package parte5.ui;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import parte5.data.DatabaseException;
import parte5.model.Aluno;
import parte5.service.AlunoNotFoundException;
import parte5.service.AlunoService;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListarAlunosJFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private AlunoService alunoService;

    public ListarAlunosJFrame(AlunoService alunoService) {
        super("Lista de Alunos");
        this.alunoService = alunoService;
        setupComponents();
        setupContextualMenu();
        loadAlunos();
    }

    private void setupComponents() {
        tableModel = new DefaultTableModel(new Object[] { "ID", "Nome", "Prontuário", "Email", "Ativo" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar o JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadAlunos() {
        try {
            List<Aluno> alunos = alunoService.findAll();
            for (Aluno aluno : alunos) {
                Object[] rowData = { aluno.getId(), aluno.getNome(), aluno.getProntuario(), aluno.getEmail(),
                        aluno.getAtivo() };
                tableModel.addRow(rowData);
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(
                    ListarAlunosJFrame.this,
                    ex.getMessage(),
                    "Erro ao listar Aluno",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupContextualMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteMenuItem = new JMenuItem("Deletar");
        JMenuItem editMenuItem = new JMenuItem("Editar");
        popupMenu.add(deleteMenuItem);
        popupMenu.add(editMenuItem);

        deleteMenuItem.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            int alunoId = (int) table.getValueAt(rowIndex, 0);
            int deletar = JOptionPane.showConfirmDialog(
                    ListarAlunosJFrame.this,
                    "Certeza que deseja deletar o aluno de código " + alunoId,
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (deletar == JOptionPane.YES_OPTION) {
                try {
                    alunoService.deletar(alunoId);
                    // tableModel.removeRow(rowIndex);
                    // ou
                    tableModel.setRowCount(0);
                    this.loadAlunos();

                } catch (AlunoNotFoundException ex) {
                    JOptionPane.showMessageDialog(
                            ListarAlunosJFrame.this,
                            ex.getMessage(),
                            "Aluno não encontrado",
                            JOptionPane.ERROR_MESSAGE);
                } catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(
                            ListarAlunosJFrame.this,
                            ex.getMessage(),
                            "Erro ao deletar Aluno",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Listener para a opção "Editar" do menu de contexto
        editMenuItem.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            int alunoId = (int) table.getValueAt(rowIndex, 0);
            System.out.println(alunoId);
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && e.getComponent() instanceof JTable) {
                    int rowIndex = table.rowAtPoint(e.getPoint());
                    if (rowIndex >= 0 && rowIndex < table.getRowCount()) {
                        table.setRowSelectionInterval(rowIndex, rowIndex);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });
    }
}
