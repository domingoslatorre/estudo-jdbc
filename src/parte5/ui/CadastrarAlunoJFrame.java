package parte5.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import parte5.model.Aluno;
import parte5.service.AlunoAlreadyExistsException;
import parte5.service.AlunoService;
import parte5.data.DatabaseException;

public class CadastrarAlunoJFrame extends JFrame {
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JLabel prontuarioLabel;
    private JTextField prontuarioField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JButton cadastrarButton;
    private JCheckBox ativoCheckBox;
    private JPanel panel;
    private AlunoService alunoService;

    public CadastrarAlunoJFrame(AlunoService alunoService) {
        super("Cadastrar Novo Aluno");
        this.alunoService = alunoService;
        setupComponents();
    }

    private void setupComponents() {
        nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);
        prontuarioLabel = new JLabel("Prontuário:");
        prontuarioField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        cadastrarButton = new JButton("Cadastrar");
        ativoCheckBox = new JCheckBox("dsadasdas", true);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(prontuarioLabel);
        panel.add(prontuarioField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(ativoCheckBox);
        panel.add(new JLabel(""));
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String prontuario = prontuarioField.getText();
                String email = emailField.getText();
                Boolean ativo = ativoCheckBox.isSelected();

                // TODO: Validar dados da UI

                Aluno newAluno = new Aluno(nome, prontuario, email, ativo);

                // Regras de negócio
                try {
                    Aluno aluno = alunoService.cadastrar(newAluno);
                    JOptionPane.showMessageDialog(CadastrarAlunoJFrame.this, aluno);
                    // cleanComponents();
                    dispose();
                } catch (AlunoAlreadyExistsException ex) {
                    JOptionPane.showMessageDialog(
                            CadastrarAlunoJFrame.this,
                            ex.getMessage(),
                            "Erro ao cadastrar Aluno",
                            JOptionPane.ERROR_MESSAGE);
                } catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(
                            CadastrarAlunoJFrame.this,
                            ex.getMessage(),
                            "Erro ao cadastrar Aluno",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // DISPOSE_ON_CLOSE: a janela seja liberada e seus recursos sejam liberados,
        // mas o programa continua em execução.
        // EXIT_ON_CLOSE: o programa é encerrado chamando System.exit(0)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(panel);
        setSize(300, 200);
        setLocationRelativeTo(null); // Centralizar o JFrame
        setResizable(false);
        setVisible(true);
    }

    private void cleanComponents() {
        nomeField.setText("");
        prontuarioField.setText("");
        emailField.setText("");
        ativoCheckBox.setSelected(true);
    }
}
