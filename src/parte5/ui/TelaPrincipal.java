package parte5.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import parte5.service.AlunoService;

public class TelaPrincipal extends JFrame {
    private AlunoService alunoService;

    public TelaPrincipal(AlunoService alunoService) {
        super("Tela Principal");
        this.alunoService = alunoService;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton cadastrarButton = new JButton("Cadastrar Aluno");
        JButton listarAlunosButton = new JButton("Listar Alunos");

        cadastrarButton.addActionListener(e -> new CadastrarAlunoJFrame(alunoService));

        listarAlunosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListarAlunosJFrame(alunoService);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(cadastrarButton);
        panel.add(listarAlunosButton);

        setupMenu();
        getContentPane().add(panel);
        setSize(500, 200);
        setVisible(true);
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu alunoMenu = new JMenu("Aluno");
        JMenuItem cadastrarMenuItem = new JMenuItem("Cadastrar Aluno");
        JMenuItem listarAlunosMenuItem = new JMenuItem("Listar Alunos");

        cadastrarMenuItem.addActionListener(e -> new CadastrarAlunoJFrame(alunoService));
        listarAlunosMenuItem.addActionListener(e -> new ListarAlunosJFrame(alunoService));
        alunoMenu.add(cadastrarMenuItem);
        alunoMenu.add(listarAlunosMenuItem);
        menuBar.add(alunoMenu);

        JMenu lookAndFeelMenu = new JMenu("Look and Feel");
        JMenuItem systemLookAndFeelMenuItem = new JMenuItem("System Look and Feel");
        JMenuItem crossPlatformLookAndFeelMenuItem = new JMenuItem("Cross-Platform Look and Feel");
        JMenuItem metalLookAndFeelMenuItem = new JMenuItem("Metal Look and Feel");
        JMenuItem nimbusLookAndFeelMenuItem = new JMenuItem("Nimbus Look and Feel");
        JMenuItem windowsLookAndFeelMenuItem = new JMenuItem("Windows Look and Feel");
        JMenuItem motifLookAndFeelMenuItem = new JMenuItem("Motif Look and Feel");

        systemLookAndFeelMenuItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(TelaPrincipal.this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        crossPlatformLookAndFeelMenuItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(TelaPrincipal.this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                String lookAndFeelName = "";

                switch (actionCommand) {
                    case "Metal Look and Feel":
                        lookAndFeelName = "javax.swing.plaf.metal.MetalLookAndFeel";
                        break;
                    case "Nimbus Look and Feel":
                        lookAndFeelName = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
                        break;
                    case "Windows Look and Feel":
                        lookAndFeelName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                        break;
                    case "Motif Look and Feel":
                        lookAndFeelName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                        break;
                    default:
                        break;
                }

                try {
                    UIManager.setLookAndFeel(lookAndFeelName);
                    SwingUtilities.updateComponentTreeUI(TelaPrincipal.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        metalLookAndFeelMenuItem.addActionListener(action);
        nimbusLookAndFeelMenuItem.addActionListener(action);
        windowsLookAndFeelMenuItem.addActionListener(action);
        motifLookAndFeelMenuItem.addActionListener(action);

        lookAndFeelMenu.add(systemLookAndFeelMenuItem);
        lookAndFeelMenu.add(crossPlatformLookAndFeelMenuItem);
        lookAndFeelMenu.add(metalLookAndFeelMenuItem);
        lookAndFeelMenu.add(nimbusLookAndFeelMenuItem);
        lookAndFeelMenu.add(windowsLookAndFeelMenuItem);
        lookAndFeelMenu.add(motifLookAndFeelMenuItem);
        menuBar.add(lookAndFeelMenu);

        setJMenuBar(menuBar);
    }
}
