package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import gradehorario.GradeHorarioController;
import gradehorario.Professor;

public class ProfessoresPanel extends JPanel {
    private GradeHorarioController controller;
    private JTextField txtNomeProfessor;
    private JTextField txtIdProfessor;
    private JList<String> professorList;
    private DefaultListModel<String> listModel;
    private JLabel lblMensagem;

    public ProfessoresPanel(GradeHorarioController controller) {
        this.controller = controller;
        initialize();
        updateProfessorList();
    }

    private void initialize() {
        setLayout(null);
        setOpaque(false);

        JLabel lblCadastrarProfessor = new JLabel("Cadastrar Professor");
        lblCadastrarProfessor.setForeground(new Color(242, 242, 242));
        lblCadastrarProfessor.setFont(new Font("Lucida Console", Font.BOLD, 19));
        lblCadastrarProfessor.setBounds(128, 115, 240, 25);
        add(lblCadastrarProfessor);

        JLabel lblNomeProfessor = new JLabel("Nome:");
        lblNomeProfessor.setForeground(new Color(242, 242, 242));
        lblNomeProfessor.setFont(new Font("Arial", Font.BOLD, 15));
        lblNomeProfessor.setBounds(82, 167, 50, 25);
        add(lblNomeProfessor);

        txtNomeProfessor = new JTextField();
        txtNomeProfessor.setBounds(142, 168, 200, 25);
        txtNomeProfessor.setBackground(new Color(62, 62, 62));
        txtNomeProfessor.setForeground(new Color(255, 255, 255));
        txtNomeProfessor.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtNomeProfessor);

        JLabel lblIdProfessor = new JLabel("ID:");
        lblIdProfessor.setForeground(new Color(242, 242, 242));
        lblIdProfessor.setFont(new Font("Arial", Font.BOLD, 15));
        lblIdProfessor.setBounds(82, 203, 50, 25);
        add(lblIdProfessor);

        txtIdProfessor = new JTextField();
        txtIdProfessor.setBounds(142, 204, 200, 25);
        txtIdProfessor.setBackground(new Color(62, 62, 62));
        txtIdProfessor.setForeground(Color.WHITE);
        txtIdProfessor.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtIdProfessor);

        JButton btnCadastrarProfessor = new JButton("Cadastrar");
        btnCadastrarProfessor.setBounds(180, 276, 100, 25);
        btnCadastrarProfessor.setBackground(new Color(23, 23, 23));
        btnCadastrarProfessor.setForeground(new Color(242, 242, 242));
        btnCadastrarProfessor.setFont(new Font("Arial", Font.PLAIN, 15));
        btnCadastrarProfessor.setFocusPainted(false);
        btnCadastrarProfessor.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnCadastrarProfessor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (controller != null) {
                        int idProfessor = Integer.parseInt(txtIdProfessor.getText().trim());
                        String nomeProfessor = txtNomeProfessor.getText().trim();
                        if (!nomeProfessor.isEmpty()) {
                            String resultado = controller.adicionarProfessor(idProfessor, nomeProfessor);
                            setMensagem(resultado);
                            txtIdProfessor.setText("");
                            txtNomeProfessor.setText("");
                            updateProfessorList();
                        } else {
                            setMensagem("Por favor, insira um nome válido para o professor.");
                        }
                    } else {
                        setMensagem("O controle de grade horária não foi inicializado corretamente.");
                    }
                } catch (NumberFormatException ex) {
                    setMensagem("Por favor, insira um ID válido para o professor.");
                } catch (IllegalArgumentException ex) {
                    setMensagem(ex.getMessage());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadastrarProfessor.setForeground(new Color(192, 192, 192));
                btnCadastrarProfessor.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCadastrarProfessor.setForeground(new Color(242, 242, 242));
                btnCadastrarProfessor.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });
        add(btnCadastrarProfessor);


        JLabel lblProfessoresCadastrados = new JLabel("Professores Cadastrados");
        lblProfessoresCadastrados.setForeground(new Color(242, 242, 242));
        lblProfessoresCadastrados.setFont(new Font("Lucida Console", Font.BOLD, 19));
        lblProfessoresCadastrados.setBounds(730, 110, 338, 30);
        add(lblProfessoresCadastrados);

        listModel = new DefaultListModel<>();
        professorList = new JList<>(listModel);
        professorList.setForeground(Color.WHITE);
        professorList.setFont(new Font("Arial", Font.PLAIN, 12));
        professorList.setEnabled(false);
        professorList.setOpaque(false);
        professorList.setFixedCellHeight(40);
        professorList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setOpaque(false);
                return label;
            }
        });

        JScrollPane scrollPaneProfessores = new JScrollPane(professorList);
        scrollPaneProfessores.setBounds(730, 146, 280, 357);
        scrollPaneProfessores.setBorder(null);
        scrollPaneProfessores.setOpaque(false);
        scrollPaneProfessores.getViewport().setOpaque(false);
        add(scrollPaneProfessores);

        // Label para exibir a mensagem de sucesso
        lblMensagem = new JLabel();
        lblMensagem.setForeground(Color.GREEN);
        lblMensagem.setFont(new Font("Arial", Font.BOLD, 12)); // Alterada para fonte menor
        lblMensagem.setBounds(120, 240, 400, 25); // Redimensionada conforme necessário
        add(lblMensagem);

        updateProfessorList();
    }

    private void setMensagem(String mensagem) {
        lblMensagem.setText(mensagem);

        // Configura um Timer para limpar a mensagem após alguns segundos
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMensagem.setText(""); // Limpa a mensagem
            }
        });
        timer.setRepeats(false); // Define o Timer para ser executado apenas uma vez
        timer.start(); // Inicia o Timer
    }

    private void updateProfessorList() {
        listModel.clear();
        for (Map.Entry<Integer, Professor> entry : controller.getProfessores().entrySet()) {
            listModel.addElement("<html>Nome: " + entry.getValue().getNome() + "<br>ID: " + entry.getKey() + "</html>");
            
        }
    }

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

