package gui;

import gradehorario.GerenciadorDeArquivos;
import gradehorario.GradeHorarioController;
import gradehorario.Materia;
import gradehorario.Turma;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HorariosPanel extends JPanel {
    private GradeHorarioController controller;
    private JTextField idTurmaTextField;
    private JPanel gradePanel;
    private JPanel buttonsPanel;
    private GerenciadorDeArquivos gerenciador;
    private JLabel mensagemErroLabel; // Label para exibir mensagens de erro
    private JLabel mensagemSucessoLabel; // Label para exibir mensagens de sucesso

    public HorariosPanel(GradeHorarioController controller) {
        this.controller = controller;
        this.gerenciador = new GerenciadorDeArquivos("C:\\Users\\ericn\\OneDrive\\Área de Trabalho\\Horários das turmas");
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel titleLabel = new JLabel("Gerar Grade de Horário");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(50, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 50, 20));
        inputPanel.setOpaque(false);

        JLabel idTurmaLabel = new JLabel("ID da Turma:");
        idTurmaLabel.setForeground(Color.WHITE);
        inputPanel.add(idTurmaLabel);

        idTurmaTextField = new JTextField(10);
        idTurmaTextField.setBackground(new Color(62, 62, 62));
        idTurmaTextField.setForeground(Color.WHITE);
        idTurmaTextField.setBorder(new EmptyBorder(5, 5, 5, 5));
        inputPanel.add(idTurmaTextField);

        JButton generateButton = new JButton("Gerar");
        generateButton.setBackground(new Color(23, 23, 23));
        generateButton.setForeground(new Color(242, 242, 242));
        generateButton.setFont(new Font("Arial", Font.PLAIN, 14));
        generateButton.setFocusPainted(false);
        generateButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTurmaText = idTurmaTextField.getText();
                if (idTurmaText.isEmpty()) {
                    mensagemErroLabel.setText("Preencha o ID da Turma.");
                    // Configura um Timer para limpar a mensagem de erro após 2 segundos
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mensagemErroLabel.setText(""); // Limpa a mensagem de erro
                        }
                    });
                    timer.setRepeats(false); // Define o Timer para ser executado apenas uma vez
                    timer.start(); // Inicia o Timer
                } else {
                    try {
                        int idTurma = Integer.parseInt(idTurmaText);
                        gerarGradeHorario(idTurma);
                        idTurmaTextField.setText("");
                        mensagemErroLabel.setText(""); // Limpa a mensagem de erro se não houver erro
                    } catch (NumberFormatException ex) {
                        mensagemErroLabel.setText("ID da Turma inválido."); // Exibe mensagem de erro se o ID não for um número
                        // Configura um Timer para limpar a mensagem de erro após 2 segundos
                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mensagemErroLabel.setText(""); // Limpa a mensagem de erro
                            }
                        });
                        timer.setRepeats(false); // Define o Timer para ser executado apenas uma vez
                        timer.start(); // Inicia o Timer
                    }
                }
            }
        });
        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                generateButton.setForeground(new Color(192, 192, 192));
                generateButton.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                generateButton.setForeground(new Color(242, 242, 242));
                generateButton.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        });
        inputPanel.add(generateButton);

        // Adicionando a label para exibir mensagens de erro logo abaixo do botão "Gerar"
        mensagemErroLabel = new JLabel();
        mensagemErroLabel.setForeground(Color.RED); // Configuração da cor da mensagem de erro
        inputPanel.add(mensagemErroLabel);

        add(inputPanel, BorderLayout.CENTER);

        gradePanel = new JPanel();
        gradePanel.setLayout(new BorderLayout());
        gradePanel.setOpaque(false);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 150, 0);
        centerPanel.add(gradePanel, gbc);

        add(centerPanel, BorderLayout.SOUTH);
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

    private void gerarGradeHorario(int idTurma) {
        Turma turma = controller.getTurma(idTurma);
        if (turma != null) {
            gradePanel.removeAll();

            JLabel turmaTitleLabel = new JLabel("Horário do " + turma.getNome());
            turmaTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            turmaTitleLabel.setFont(new Font("Arial", Font.BOLD, 17));
            turmaTitleLabel.setForeground(Color.WHITE);
            gradePanel.add(turmaTitleLabel, BorderLayout.NORTH);

            JPanel innerGradePanel = new JPanel();
            innerGradePanel.setLayout(new GridLayout(6, 6, 10, 10)); // Adicionando espaçamento entre as células
            innerGradePanel.setOpaque(false);
            int cellWidth = 170; // Largura fixa para cada célula
            int cellHeight = 30; // Altura fixa para cada célula
            innerGradePanel.setPreferredSize(new Dimension(cellWidth * 6, cellHeight * 6)); // Definindo a largura e altura total da grade

            controller.gerarGradeHorario(idTurma);

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                    cellLabel.setOpaque(false);
                    cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    cellLabel.setForeground(Color.WHITE);
                    if (i == 0 && j == 0) {
                        cellLabel.setText("Horário");
                        cellLabel.setOpaque(true);
                        cellLabel.setBackground(Color.WHITE);
                        cellLabel.setForeground(Color.BLACK);
                    } else if (i == 0) {
                        cellLabel.setText(getDiaSemana(j));
                        cellLabel.setOpaque(true);
                        cellLabel.setBackground(Color.WHITE);
                        cellLabel.setForeground(Color.BLACK);
                    } else if (j == 0) {
                        cellLabel.setText(i + "º Aula");
                        cellLabel.setOpaque(true);
                        cellLabel.setBackground(Color.WHITE);
                        cellLabel.setForeground(Color.BLACK);
                    } else {
                        Materia materia = turma.getPosicaoDia(j)[i - 1];
                        String materiaNome = materia != null ? materia.getNome() : "Disponível";
                        cellLabel.setText("   " + materiaNome); // Adicionando espaços extras antes do nome da matéria
                        cellLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Definindo a fonte para Arial Plain com tamanho 12
                        String professorNome = (materia != null && materia.getProfessor() != null) ? " - Prof. " + materia.getProfessor().getNome() : "";
                        cellLabel.setText(cellLabel.getText() + professorNome);
                    }
                    innerGradePanel.add(cellLabel);
                }
            }
            gradePanel.add(innerGradePanel, BorderLayout.CENTER);

            // Painel para os botões "Sim" e "Não"
            buttonsPanel = new JPanel();
            buttonsPanel.setOpaque(false);
            buttonsPanel.setLayout(new BorderLayout(10, 10));

            JPanel questionPanel = new JPanel();
            questionPanel.setOpaque(false);
            questionPanel.setLayout(new GridLayout(1, 1));
            JLabel saveTitleLabel = new JLabel("Deseja salvar esse horário?");
            saveTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            saveTitleLabel.setFont(new Font("Arial", Font.BOLD, 17));
            saveTitleLabel.setForeground(Color.WHITE);
            questionPanel.add(saveTitleLabel);

            JPanel buttonsSubPanel = new JPanel();
            buttonsSubPanel.setOpaque(false);
            buttonsSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Botão "Sim"
            JButton saveButton = new JButton("Sim");
            saveButton.setBackground(new Color(23, 23, 23));
            saveButton.setForeground(new Color(242, 242, 242));
            saveButton.setFont(new Font("Arial", Font.PLAIN, 15));
            saveButton.setFocusPainted(false);
            saveButton.setBorder(new EmptyBorder(5, 5, 5, 5));
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gerenciador.criarPlanilhaExcel(turma);
                    mensagemSucessoLabel.setText("Horário salvo com sucesso!"); // Exibe a mensagem de sucesso
                    // Configura um Timer para limpar a mensagem de sucesso após 2 segundos
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mensagemSucessoLabel.setText(""); // Limpa a mensagem de sucesso
                        }
                    });
                    timer.setRepeats(false); // Define o Timer para ser executado apenas uma vez
                    timer.start(); // Inicia o Timer
                }
            });
            saveButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    saveButton.setForeground(new Color(192, 192, 192));
                    saveButton.setFont(new Font("Arial", Font.BOLD, 15));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    saveButton.setForeground(new Color(242, 242, 242));
                    saveButton.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            });
            buttonsSubPanel.add(saveButton);

            // Botão "Não"
            JButton cancelButton = new JButton("Não");
            cancelButton.setBackground(new Color(23, 23, 23));
            cancelButton.setForeground(new Color(242, 242, 242));
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
            cancelButton.setFocusPainted(false);
            cancelButton.setBorder(new EmptyBorder(5, 5, 5, 5));
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gradePanel.removeAll();
                    gradePanel.revalidate();
                    gradePanel.repaint();
                }
            });
            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    cancelButton.setForeground(new Color(192, 192, 192));
                    cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cancelButton.setForeground(new Color(242, 242, 242));
                    cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            });
            buttonsSubPanel.add(cancelButton);

            buttonsPanel.add(questionPanel, BorderLayout.NORTH);
            buttonsPanel.add(buttonsSubPanel, BorderLayout.CENTER);

            // Adicionando a label de mensagem de sucesso logo abaixo dos botões
            mensagemSucessoLabel = new JLabel("", SwingConstants.CENTER);
            mensagemSucessoLabel.setForeground(Color.GREEN); // Configurando a cor da mensagem de sucesso
            buttonsPanel.add(mensagemSucessoLabel, BorderLayout.SOUTH);

            gradePanel.add(buttonsPanel, BorderLayout.SOUTH);

            gradePanel.revalidate();
            gradePanel.repaint();
        } else {
            mensagemErroLabel.setText("Turma não encontrada."); // Exibe mensagem de erro se a turma não for encontrada
            // Configura um Timer para limpar a mensagem de erro após 2 segundos
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mensagemErroLabel.setText(""); // Limpa a mensagem de erro
                }
            });
            timer.setRepeats(false); // Define o Timer para ser executado apenas uma vez
            timer.start(); // Inicia o Timer
        }
    }

    private String getDiaSemana(int index) {
        switch (index) {
            case 1:
                return "Segunda-feira";
            case 2:
                return "Terça-feira";
            case 3:
                return "Quarta-feira";
            case 4:
                return "Quinta-feira";
            case 5:
                return "Sexta-feira";
            default:
                return "";
        }
    }
}
