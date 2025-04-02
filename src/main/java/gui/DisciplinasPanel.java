package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import gradehorario.GradeHorarioController;
import gradehorario.Materia;

public class DisciplinasPanel extends JPanel {
    private DefaultListModel<String> disciplinaListModel;
    private GradeHorarioController controller;
    private JLabel lblMensagem;

    public DisciplinasPanel(GradeHorarioController controller) {
        this.controller = controller;
        this.disciplinaListModel = new DefaultListModel<>();
        initialize();
        updateMateriaList(); // Chama o método para atualizar a lista de disciplinas
    }

    private void initialize() {
        setLayout(null);
        setOpaque(false);

        JLabel lblNomeDisciplina = new JLabel("Nome:");
        lblNomeDisciplina.setForeground(new Color(242, 242, 242));
        lblNomeDisciplina.setFont(new Font("Arial", Font.BOLD, 15));
        lblNomeDisciplina.setBounds(82, 167, 50, 25);
        add(lblNomeDisciplina);

        JTextField txtNomeDisciplina = new JTextField();
        txtNomeDisciplina.setBounds(142, 168, 200, 25);
        txtNomeDisciplina.setBackground(new Color(62, 62, 62));
        txtNomeDisciplina.setForeground(new Color(255, 255, 255));
        txtNomeDisciplina.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtNomeDisciplina);

        JLabel lblIDDisciplina = new JLabel("ID:");
        lblIDDisciplina.setForeground(new Color(242, 242, 242));
        lblIDDisciplina.setFont(new Font("Arial", Font.BOLD, 15));
        lblIDDisciplina.setBounds(82, 203, 50, 25);
        add(lblIDDisciplina);

        JTextField txtIDDisciplina = new JTextField();
        txtIDDisciplina.setBounds(142, 204, 200, 25);
        txtIDDisciplina.setBackground(new Color(62, 62, 62));
        txtIDDisciplina.setForeground(Color.WHITE);
        txtIDDisciplina.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtIDDisciplina);

        JLabel lblProfessorID = new JLabel("ID do Professor:");
        lblProfessorID.setForeground(new Color(242, 242, 242));
        lblProfessorID.setFont(new Font("Arial", Font.BOLD, 15));
        lblProfessorID.setBounds(25, 239, 125, 25);
        add(lblProfessorID);

        JTextField txtProfessorID = new JTextField();
        txtProfessorID.setBounds(142, 240, 200, 25);
        txtProfessorID.setBackground(new Color(62, 62, 62));
        txtProfessorID.setForeground(Color.WHITE);
        txtProfessorID.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(txtProfessorID);

        JButton btnCadastrarDisciplina = new JButton("Cadastrar");
        btnCadastrarDisciplina.setBounds(180, 312, 100, 25);
        btnCadastrarDisciplina.setBackground(new Color(23, 23, 23));
        btnCadastrarDisciplina.setForeground(new Color(242, 242, 242));
        btnCadastrarDisciplina.setFont(new Font("Arial", Font.PLAIN, 15));
        btnCadastrarDisciplina.setFocusPainted(false);
        btnCadastrarDisciplina.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnCadastrarDisciplina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nome = txtNomeDisciplina.getText();
                String idString = txtIDDisciplina.getText();
                String professorIdString = txtProfessorID.getText();
                if (!nome.isEmpty() && !idString.isEmpty() && !professorIdString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);
                        int professorId = Integer.parseInt(professorIdString);
                        if (controller.getMaterias().containsKey(id)) {
                            setMensagem("ID de disciplina já existe.");
                            return;
                        }
                        if (!controller.getProfessores().containsKey(professorId)) {
                            setMensagem("ID de professor não encontrado.");
                            return;
                        }
                        
                        String professorName = controller.getProfessores().get(professorId).getNome();
                        disciplinaListModel.addElement("\nNome: " + nome + "  |   ID: " + id + "\nProf.: " + professorName + "\n");
                        controller.criarMateria(id, nome);
                        controller.adicionarProfessorNaMateria(professorId, id);
                        txtNomeDisciplina.setText("");
                        txtIDDisciplina.setText("");
                        txtProfessorID.setText("");
                        setMensagem("DISCIPLINA ADICIONADA COM SUCESSO!");
                        updateMateriaList(); // Atualiza a lista de disciplinas após cadastrar uma nova
                    } catch (NumberFormatException ex) {
                        setMensagem("IDs devem ser números inteiros.");
                    } catch (IllegalArgumentException ex) {
                        setMensagem(ex.getMessage());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadastrarDisciplina.setForeground(new Color(192, 192, 192));
                btnCadastrarDisciplina.setFont(new Font("Arial", Font.BOLD, 17));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCadastrarDisciplina.setForeground(new Color(242, 242, 242));
                btnCadastrarDisciplina.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });
        add(btnCadastrarDisciplina);

        JLabel lblDisciplinasCadastradas = new JLabel("Disciplinas Cadastradas");
        lblDisciplinasCadastradas.setForeground(new Color(242, 242, 242));
        lblDisciplinasCadastradas.setFont(new Font("Lucida Console", Font.BOLD, 19));
        lblDisciplinasCadastradas.setBounds(730, 110, 338, 30);
        add(lblDisciplinasCadastradas);

        JScrollPane scrollPaneDisciplinas = new JScrollPane();
        scrollPaneDisciplinas.setBounds(730, 146, 280, 357);
        scrollPaneDisciplinas.setBorder(null);
        scrollPaneDisciplinas.setOpaque(false);
        scrollPaneDisciplinas.getViewport().setOpaque(false);
        add(scrollPaneDisciplinas);
        JList<String> disciplinaList = new JList<>(disciplinaListModel);
        scrollPaneDisciplinas.setViewportView(disciplinaList);
        disciplinaList.setForeground(Color.WHITE);
        disciplinaList.setFont(new Font("Arial", Font.PLAIN, 12));
        disciplinaList.setEnabled(false);
        disciplinaList.setOpaque(false);
        disciplinaList.setFixedCellHeight(40);
        disciplinaList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    String[] lines = value.toString().split("\n");
                    StringBuilder formattedText = new StringBuilder("<html>");
                    for (String line : lines) {
                        formattedText.append(line).append("<br>");
                    }
                    formattedText.append("</html>");
                    label.setText(formattedText.toString());
                }
                label.setOpaque(false);
                return label;
            }
        });

        JLabel lblCadastrarDisciplina = new JLabel("Cadastrar Disciplina");
        lblCadastrarDisciplina.setForeground(new Color(242, 242, 242));
        lblCadastrarDisciplina.setFont(new Font("Lucida Console", Font.BOLD, 19));
        lblCadastrarDisciplina.setBounds(128, 115, 240, 25);
        add(lblCadastrarDisciplina);

        // Label para exibir a mensagem de sucesso
        lblMensagem = new JLabel();
        lblMensagem.setForeground(Color.GREEN);
        lblMensagem.setFont(new Font("Arial", Font.BOLD, 12));// Alterada para fonte menor
        lblMensagem.setBounds(120, 275, 400, 25); // Redimensionada conforme necessário
        add(lblMensagem);

        // Adicionando uma linha invisível entre os elementos da lista
        disciplinaListModel.addElement(" "); // Adiciona um espaço em branco

        // Adicionando uma linha divisória entre os elementos da lista
        disciplinaList.setFixedCellHeight(40);
        disciplinaList.setFixedCellWidth(280);
        disciplinaList.setOpaque(false);
        disciplinaList.setBorder(null);
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

    // Método para atualizar a lista de disciplinas
    private void updateMateriaList() {
        disciplinaListModel.clear();
        for (Map.Entry<Integer, Materia> entry : controller.getMaterias().entrySet()) {
            Materia materia = entry.getValue();
            String professorName = "N/A";
            if (materia.getProfessor() != null) {
                professorName = materia.getProfessor().getNome();
            }
            disciplinaListModel.addElement("Nome: " + materia.getNome() + "  |   ID: " + entry.getKey() + "\nProf.: " + professorName + "\n");
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

