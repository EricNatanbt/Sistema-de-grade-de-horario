package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gradehorario.GradeHorarioController;

public class TurmasPanel extends JPanel {
    private DefaultListModel<String> turmaListModel;
    private GradeHorarioController controller;
    private JLabel lblMensagem;

    public TurmasPanel(GradeHorarioController controller) {
        this.controller = controller;
        this.turmaListModel = new DefaultListModel<>();
        initialize();
    }

    private void initialize() {
        setLayout(null);
        setOpaque(false);

        JLabel lblNomeTurma = new JLabel("Nome:");
        lblNomeTurma.setForeground(new Color(242, 242, 242));
        lblNomeTurma.setFont(new Font("Arial", Font.BOLD, 15));
        lblNomeTurma.setBounds(82, 167, 50, 25);
        add(lblNomeTurma);

        JTextField txtNomeTurma = new JTextField();
        txtNomeTurma.setBounds(142, 168, 200, 25);
        txtNomeTurma.setBackground(new Color(62, 62, 62));
        txtNomeTurma.setForeground(new Color(255, 255, 255));
        txtNomeTurma.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtNomeTurma);

        JLabel lblIDTurma = new JLabel("ID:");
        lblIDTurma.setForeground(new Color(242, 242, 242));
        lblIDTurma.setFont(new Font("Arial", Font.BOLD, 15));
        lblIDTurma.setBounds(82, 203, 50, 25);
        add(lblIDTurma);

        JTextField txtIDTurma = new JTextField();
        txtIDTurma.setBounds(142, 204, 200, 25);
        txtIDTurma.setBackground(new Color(62, 62, 62));
        txtIDTurma.setForeground(Color.WHITE);
        txtIDTurma.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtIDTurma);

        JLabel lblMateriasTurma = new JLabel("Matéria(s) ID(s):");
        lblMateriasTurma.setForeground(new Color(242, 242, 242));
        lblMateriasTurma.setFont(new Font("Arial", Font.BOLD, 15));
        lblMateriasTurma.setBounds(10, 239, 150, 25); // Ajustado o posicionamento para evitar sobreposição
        add(lblMateriasTurma);

        JTextField txtMateriasTurma = new JTextField();
        txtMateriasTurma.setBounds(142, 240, 200, 25);
        txtMateriasTurma.setBackground(new Color(62, 62, 62));
        txtMateriasTurma.setForeground(Color.WHITE);
        txtMateriasTurma.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtMateriasTurma);

        JButton btnCadastrarTurma = new JButton("Cadastrar");
        btnCadastrarTurma.setBounds(180, 312, 100, 25);
        btnCadastrarTurma.setBackground(new Color(23, 23, 23));
        btnCadastrarTurma.setForeground(new Color(242, 242, 242));
        btnCadastrarTurma.setFont(new Font("Arial", Font.PLAIN, 15));
        btnCadastrarTurma.setFocusPainted(false);
        btnCadastrarTurma.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnCadastrarTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNomeTurma.getText();
                String idString = txtIDTurma.getText();
                String materiasString = txtMateriasTurma.getText();
                if (!nome.isEmpty() && !idString.isEmpty() && !materiasString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);
                        String[] materiasIdsStr = materiasString.split(",");
                        List<Integer> materiasIds = new ArrayList<>();
                        for (String materiaId : materiasIdsStr) {
                            materiasIds.add(Integer.parseInt(materiaId.trim()));
                        }
                        String resultado = controller.criarTurma(id, nome, materiasIds);
                        setMensagem("Turma cadastrada com sucesso!");

                        turmaListModel.addElement("<html>Nome: " + nome + "<br>ID: " + id + "</html>");
                        turmaListModel.addElement("<html> Matérias IDs: " + materiasString);
                        turmaListModel.addElement(" ");

                        txtNomeTurma.setText("");
                        txtIDTurma.setText("");
                        txtMateriasTurma.setText("");
                    } catch (NumberFormatException ex) {
                        setMensagem("IDs devem ser números inteiros.");
                    } catch (IllegalArgumentException ex) {
                        setMensagem(ex.getMessage());
                    }
                } else {
                    setMensagem("Por favor, preencha todos os campos.");
                }
            }
        });
        add(btnCadastrarTurma);

        JLabel lblTurmasCadastradas = new JLabel("Turmas Cadastradas");
        lblTurmasCadastradas.setForeground(new Color(242, 242, 242));
        lblTurmasCadastradas.setFont(new Font("Lucida Console", Font.BOLD, 19));
        lblTurmasCadastradas.setBounds(730, 110, 338, 30);
        add(lblTurmasCadastradas);
        JScrollPane scrollPaneTurmas = new JScrollPane();
        scrollPaneTurmas.setBounds(730, 146, 280, 357);
        scrollPaneTurmas.setBorder(null);
        scrollPaneTurmas.setOpaque(false);
        scrollPaneTurmas.getViewport().setOpaque(false);
        add(scrollPaneTurmas);

        JList<String> turmaList = new JList<>(turmaListModel);
        scrollPaneTurmas.setViewportView(turmaList);
        turmaList.setForeground(Color.WHITE);
        turmaList.setFont(new Font("Arial", Font.PLAIN, 12));
        turmaList.setEnabled(false);
        turmaList.setOpaque(false);

        turmaList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setOpaque(false);
                return label;
            }
        });

        JLabel lblCadastrarTurma = new JLabel("Cadastrar Turma");
        lblCadastrarTurma.setForeground(new Color(242, 242, 242));
        lblCadastrarTurma.setFont(new Font("Lucida Console", Font.BOLD, 19));
        lblCadastrarTurma.setBounds(152, 115, 227, 25);
        add(lblCadastrarTurma);

        lblMensagem = new JLabel();
        lblMensagem.setForeground(Color.GREEN);
        lblMensagem.setFont(new Font("Arial", Font.BOLD, 12));
        lblMensagem.setBounds(110, 275, 400, 25);
        add(lblMensagem);
    }

    private void setMensagem(String mensagem) {
        lblMensagem.setText(mensagem);

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMensagem.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();
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

