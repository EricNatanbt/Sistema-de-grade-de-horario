package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gradehorario.GradeHorarioController;

public class MainGUI {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DefaultListModel<String> professorListModel;
    private DefaultListModel<String> disciplinaListModel;
    private DefaultListModel<String> turmaListModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainGUI window = new MainGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainGUI() {
        initialize();
        configureKeyBindings();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23, 23, 23));
        frame.setBounds(10, 20, 1296, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBounds(0, 67, 1400, 700);
        frame.getContentPane().add(cardPanel);

        JPanel homePanel = new GradientPanel();
        homePanel.setLayout(null);
        cardPanel.add(homePanel, "homePanel");

        // Adicionando o título e descrição na homePanel
        JLabel titleLabel = new JLabel("Sistema de Gestão de Horário");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 200, 500, 30);
        homePanel.add(titleLabel);

        JLabel descriptionLabel = new JLabel("<html>Sistema de gestão acadêmica desenvolvido em Java, cuja principal funcionalidade é auxiliar na administração de horários escolares ou universitários. O sistema permite o cadastro de professores, disciplinas e turmas, e, com base nesses dados, gera uma grade de horários para as turmas cadastradas.</html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setBounds(100, 240, 800, 60);
        homePanel.add(descriptionLabel);
        
        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setForeground(new Color(242, 242, 242));
        btnIniciar.setContentAreaFilled(false); 
        btnIniciar.setBorderPainted(false); 
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 15));
        btnIniciar.setBounds(63, 300, 120, 40);
        btnIniciar.setFocusPainted(false);
        btnIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "professoresPanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnIniciar.setForeground(new Color(192, 192, 192));
                btnIniciar.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnIniciar.setForeground(new Color(242, 242, 242));
                btnIniciar.setFont(new Font("Arial", Font.BOLD, 15));
            }
        });
        homePanel.add(btnIniciar);

        professorListModel = new DefaultListModel<>(); 
        GradeHorarioController controller = new GradeHorarioController();
        JPanel professoresPanel = new ProfessoresPanel(controller);
        cardPanel.add(professoresPanel, "professoresPanel");

        disciplinaListModel = new DefaultListModel<>(); 
        JPanel disciplinasPanel = new DisciplinasPanel(controller);
        cardPanel.add(disciplinasPanel, "disciplinasPanel");

        turmaListModel = new DefaultListModel<>(); 
        JPanel turmasPanel = new TurmasPanel(controller);
        cardPanel.add(turmasPanel, "turmasPanel");

        JPanel horariosPanel = new HorariosPanel(controller);
        cardPanel.add(horariosPanel, "horariosPanel");

        Font menuFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblHome = new JLabel("INÍCIO");
        lblHome.setForeground(new Color(242, 242, 242));
        lblHome.setFont(new Font("Arial", Font.PLAIN, 14));
        lblHome.setBounds(200, 31, 56, 25);
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "homePanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblHome.setForeground(new Color(192, 192, 192));
                lblHome.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblHome.setForeground(new Color(242, 242, 242));
                lblHome.setFont(menuFont);
            }
        });
        frame.getContentPane().add(lblHome);

        JLabel lblAbout = new JLabel("PROFESSORES");
        lblAbout.setForeground(new Color(242, 242, 242));
        lblAbout.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAbout.setBounds(299, 31, 127, 25);
        lblAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "professoresPanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblAbout.setForeground(new Color(192, 192, 192));
                lblAbout.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblAbout.setForeground(new Color(242, 242, 242));
                lblAbout.setFont(menuFont);
            }
        });
        frame.getContentPane().add(lblAbout);

        JLabel lblProjects = new JLabel("DISCIPLINAS");
        lblProjects.setForeground(new Color(242, 242, 242));
        lblProjects.setFont(new Font("Arial", Font.PLAIN, 14));
        lblProjects.setBounds(466, 31, 116, 25);
        lblProjects.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "disciplinasPanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblProjects.setForeground(new Color(192, 192, 192));
                lblProjects.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblProjects.setForeground(new Color(242, 242, 242));
                lblProjects.setFont(menuFont);
            }
        });
        frame.getContentPane().add(lblProjects);

        JLabel lblContact = new JLabel("TURMAS");
        lblContact.setForeground(new Color(242, 242, 242));
        lblContact.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContact.setBounds(609, 31, 100, 25);
        lblContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "turmasPanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblContact.setForeground(new Color(192, 192, 192));
                lblContact.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblContact.setForeground(new Color(242, 242, 242));
                lblContact.setFont(menuFont);
            }
        });
        frame.getContentPane().add(lblContact);

        JLabel lblHorarios = new JLabel("HORÁRIOS");
        lblHorarios.setForeground(new Color(242, 242, 242));
        lblHorarios.setFont(new Font("Arial", Font.PLAIN, 14));
        lblHorarios.setBounds(727, 31, 100, 25);
        lblHorarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "horariosPanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblHorarios.setForeground(new Color(192, 192, 192));
                lblHorarios.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblHorarios.setForeground(new Color(242, 242, 242));
                lblHorarios.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        });
        frame.getContentPane().add(lblHorarios);

        JLabel lblSocialMedia = new JLabel("Java Project");
        lblSocialMedia.setForeground(new Color(242, 242, 242));
        lblSocialMedia.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblSocialMedia.setBounds(1256, 34, 200, 16);
        frame.getContentPane().add(lblSocialMedia);

        JLabel lblIcone = new JLabel("Icone");
        lblIcone.setEnabled(false);
        lblIcone.setIcon(new ImageIcon("C:\\Users\\Notebook - Eric\\Desktop\\Sistema Grade de Horário\\Logo.png"));
        lblIcone.setBounds(31, 11, 56, 52);
        frame.getContentPane().add(lblIcone);
    }

    private void configureKeyBindings() {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "toggleFullscreen");
        actionMap.put("toggleFullscreen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleFullscreen();
            }
        });
    }

    private void toggleFullscreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (frame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
            frame.dispose();
            frame.setUndecorated(true);
            frame.setResizable(false);
            device.setFullScreenWindow(frame);
            frame.setVisible(true); // Redraws the screen correctly
        } else {
            frame.dispose();
            frame.setUndecorated(false);
            frame.setResizable(true);
            device.setFullScreenWindow(null);
            frame.setBounds(10, 20, 1296, 700); // Sets the normal window size
            frame.setVisible(true); // Redraws the screen correctly
        }
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(23, 23, 23);
            Color color2 = new Color(60, 60, 60);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}

