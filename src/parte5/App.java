package parte5;

import javax.swing.*;
import parte5.data.AlunoDAO;
import parte5.service.AlunoService;
import parte5.ui.TelaPrincipal;

public class App extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AlunoDAO alunoDAO = new AlunoDAO();
                AlunoService alunoService = new AlunoService(alunoDAO);
                new TelaPrincipal(alunoService);
            }
        });
    }
}
