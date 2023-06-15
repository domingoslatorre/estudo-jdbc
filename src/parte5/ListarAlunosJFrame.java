package parte5;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarAlunosJFrame extends JDialog {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private AlunoDAO alunoDAO;
    
    public ListarAlunosJFrame(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
        setTitle("Listar Alunos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);

        tableModel = new DefaultTableModel(
            new Object[] { "ID", "Nome", "Prontuario", "Email", "Ativo" },
            0
        );

        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);

        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        loadData();

        getContentPane().add(panel);

        setVisible(true);
    }

    private void loadData() {
        try {
            List<Aluno> alunos = alunoDAO.findAll();

            for (Aluno aluno : alunos) {
                Object[] row = { 
                    aluno.getId(), 
                    aluno.getNome(), 
                    aluno.getProntuario(), 
                    aluno.getEmail(), 
                    aluno.getAtivo()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                ListarAlunosJFrame.this, 
                "Erro ao acessar o banco de dados",
                "Erro",
                JOptionPane.ERROR_MESSAGE    
            );
        }
    }
}
