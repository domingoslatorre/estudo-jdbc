package parte5;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CadastrarAlunoJFrame extends JFrame {
    private JLabel nomeLabel = new JLabel("Nome:");
    private JTextField nomeTextField = new JTextField(20);
    private JLabel prontuarioLabel = new JLabel("Prontuario:");
    private JTextField prontuarioTextField = new JTextField(20);
    private JLabel emailLabel = new JLabel("Email:");
    private JTextField emailTextField = new JTextField(20);
    private JCheckBox ativoCheckBox = new JCheckBox("Ativo", true);
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JPanel panel = new JPanel();

    public CadastrarAlunoJFrame() {
        setTitle("Cadastrar Novo Aluno");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.add(nomeLabel);
        panel.add(nomeTextField);
        panel.add(prontuarioLabel);
        panel.add(prontuarioTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(ativoCheckBox);
        panel.add(new JLabel(""));
        panel.add(cadastrarButton);


        cadastrarButton.addActionListener(e -> {
            String nome = nomeTextField.getText();
            String prontuario = prontuarioTextField.getText();
            String email = emailTextField.getText();
            Boolean ativo = ativoCheckBox.isSelected();


            ArrayList<String> errors = new ArrayList<>();
            
            // Validar dados
            if (nome.isBlank()) {
                errors.add("Nome não pode ser vazio");
            }

            if (nome.trim().length() < 3 || nome.trim().length() > 100) {
                errors.add("Nome deve ter entre 3 e 100 caracteres");
            }

            if (prontuario.isBlank()) {
                errors.add("Prontuario não pode ser vazio");
            }

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(
                    CadastrarAlunoJFrame.this, 
                    String.join("\n", errors),
                    "Erro ao cadastrar",
                    JOptionPane.ERROR_MESSAGE    
                );
            } else {
                try {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    Aluno aluno = new Aluno(nome, prontuario, email, ativo);
                    Aluno alunoSalvo = alunoDAO.create(aluno);
                    JOptionPane.showMessageDialog(
                        CadastrarAlunoJFrame.this, 
                        "Aluno cadastrado com sucesso id: " + alunoSalvo.getId(),
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE    
                    );
                    cleanTextFields();

                } catch (Exception ex) {
                    
                }

            }
            
        });
        
        getContentPane().add(panel);
        setResizable(false);
        setVisible(true);
    }

    private void cleanTextFields() {
        nomeTextField.setText("");
        prontuarioTextField.setText("");
        emailTextField.setText("");
    }
}
