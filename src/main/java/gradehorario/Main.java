package gradehorario;

import java.util.*;

public class Main {
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String YELLOW = "\033[0;33m";
    private static final String CYAN = "\033[0;36m";
    private static final String BOLD_RED = "\033[1;31m";
    private static final String BOLD_GREEN = "\033[1;32m";
    private static final String BOLD_YELLOW = "\033[1;33m";
    private static final String BOLD_CYAN = "\033[1;36m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeHorarioController controller = new GradeHorarioController();
        String nomePasta = "C:\\Users\\Notebook - Eric\\Desktop\\Sistema Grade de Horário\\Horários das Turmas";
        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos(nomePasta);

        // Banco de dados como exemplo
        controller.adicionarProfessor(200, "Antônio");
        controller.adicionarProfessor(201, "Lucas");
        controller.adicionarProfessor(202, "João");

        controller.criarMateria(200, "História");
        controller.criarMateria(201, "Ed. Física");
        controller.criarMateria(202, "Ciências");

        controller.adicionarProfessorNaMateria(200, 200);
        controller.adicionarProfessorNaMateria(201, 201);
        controller.adicionarProfessorNaMateria(202, 202);

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao(scanner);
            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner, controller);
                    break;
                case 2:
                    criarMateria(scanner, controller);
                    break;
                case 3:
                    cadastrarMateriaProfessor(scanner, controller);
                    break;
                case 4:
                    cadastrarTurma(scanner, controller, gerenciador);
                    break;
                case 5:
                    gerarHorario(scanner, controller, gerenciador);
                    break;
                case 6:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Erro, tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void exibirMenu() {
        System.out.println(RESET + "\n=-=-=-=-=-= " + BOLD_CYAN + "Gerenciador de Grade de Horario" + RESET + " =-=-=-=-=-=");
        System.out.println("[" + BOLD_CYAN + "1" + RESET + "] - Cadastrar Professor");
        System.out.println("[" + BOLD_CYAN + "2" + RESET + "] - Criar Materia");
        System.out.println("[" + BOLD_CYAN + "3" + RESET + "] - Cadastrar Materia-Professor");
        System.out.println("[" + BOLD_CYAN + "4" + RESET + "] - Cadastrar Turma");
        System.out.println("[" + BOLD_CYAN + "5" + RESET + "] - Gerar Grade de Horário");
        System.out.println("[" + BOLD_CYAN + "6" + RESET + "] - Finalizar programa");
        System.out.println("--------------------------------------------------------");
        System.out.print("Digite o que deseja fazer: " + BOLD_CYAN);
    }

    private static int lerOpcao(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Erro, tente novamente.");
            scanner.next();
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private static void cadastrarProfessor(Scanner scanner, GradeHorarioController controller) {
        while (true) {
            System.out.println(RESET + "\n=-=-=-=-=-= " + BOLD_YELLOW + "Cadastrar Professor" + RESET + " =-=-=-=-=-=");
            System.out.print("Digite o nome do professor: " + BOLD_YELLOW);
            String nome = scanner.nextLine();

            System.out.print(RESET + "Digite o ID do professor: " + BOLD_YELLOW);
            int id = Integer.parseInt(scanner.nextLine());
            try {
                controller.adicionarProfessor(id, nome);
                System.out.println(RESET + "Professor cadastrado com sucesso!");
                
                System.out.println("\n[" + BOLD_GREEN + "1" + RESET + "] -" + BOLD_GREEN + "Sim" + RESET);
                System.out.println("[" + BOLD_RED + "2" + RESET + "] - " + BOLD_RED + "Não" + RESET);
                System.out.print("Deseja cadastrar mais um professor? " + BOLD_YELLOW);
                int continuar = Integer.parseInt(scanner.nextLine());
                if (continuar != 1) break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }

    private static void criarMateria(Scanner scanner, GradeHorarioController controller) {
        while (true) {
            System.out.println(RESET + "\n=-=-=-=-=-= " + BOLD_RED + "Criar Materia" + RESET + " =-=-=-=-=-=");
            System.out.print("Digite o nome da matéria: " + BOLD_RED);
            String nome = scanner.nextLine();
            System.out.print(RESET + "Digite o ID da matéria: " + BOLD_RED);
            int id = Integer.parseInt(scanner.nextLine());
            try {
                controller.criarMateria(id, nome);
                System.out.println(RESET + "Matéria criada com sucesso!");
                
                System.out.println("\n[" + BOLD_GREEN + "1" + RESET + "] -" + BOLD_GREEN + "Sim" + RESET);
                System.out.println("[" + BOLD_RED + "2" + RESET + "] - " + BOLD_RED + "Não" + RESET);
                System.out.print("Deseja cadastrar mais uma matéria? " + BOLD_RED);
                int continuar = Integer.parseInt(scanner.nextLine());
                if (continuar != 1) break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }

    private static void cadastrarMateriaProfessor(Scanner scanner, GradeHorarioController controller) {
        while (true) {
            System.out.println(RESET + "\n=-=-=-=-=-=" + BOLD_CYAN + "Cadastrar Materia-Professor" + RESET + "=-=-=-=-=-=");
            
            System.out.println("________________________________");
            System.out.println(BOLD_YELLOW + "Professores:" + RESET);
            for (Map.Entry<Integer, Professor> entry : controller.getProfessores().entrySet()) {
                System.out.println(RESET + "ID: " + YELLOW + entry.getKey() + RESET + " - Nome: " + YELLOW + entry.getValue().getNome() + RESET);
            }
            System.out.println("-------------------------------");

            System.out.println(BOLD_RED + "Matérias:" + RESET);
            for (Map.Entry<Integer, Materia> entry : controller.getMaterias().entrySet()) {
                System.out.println(RESET + "ID: " + RED + entry.getKey() + RESET + " - Nome: " + RED + entry.getValue().getNome() + RESET);
            }
            System.out.println("________________________________");

            System.out.print("Digite o ID do professor: " + BOLD_CYAN);
            int idProfessor = Integer.parseInt(scanner.nextLine());
            System.out.print(RESET + "Digite o ID da matéria: " + BOLD_CYAN);
            int idMateria = Integer.parseInt(scanner.nextLine());
            try {
                controller.adicionarProfessorNaMateria(idProfessor, idMateria);
                System.out.println(RESET + "Professor cadastrado na matéria com sucesso!");
                
                System.out.println("\n[" + BOLD_GREEN + "1" + RESET + "] -" + BOLD_GREEN + "Sim" + RESET);
                System.out.println("[" + BOLD_RED + "2" + RESET + "] - " + BOLD_RED + "Não" + RESET);
                System.out.print("Deseja cadastrar mais uma matéria a um professor? " + BOLD_CYAN);
                int continuar = Integer.parseInt(scanner.nextLine());
                if (continuar != 1) break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }

    private static void cadastrarTurma(Scanner scanner, GradeHorarioController controller, GerenciadorDeArquivos gerenciador) {
        while (true) {
            System.out.println(RESET + "\n=-=-=-=-=-= " + CYAN + "Cadastrar Turma" + RESET + " =-=-=-=-=-=");
            System.out.print("Digite o nome da turma: " + BOLD_CYAN);
            String nome = scanner.nextLine();
            System.out.print(RESET + "Digite o ano da turma: " + BOLD_CYAN);
            int ano = Integer.parseInt(scanner.nextLine());
            try {
              //  controller.cadastrarTurma(nome, ano);
               // gerenciador.criarArquivoTurma(nome, ano);
                System.out.println(RESET + "Turma cadastrada com sucesso!");
                
                System.out.println("\n[" + BOLD_GREEN + "1" + RESET + "] -" + BOLD_GREEN + "Sim" + RESET);
                System.out.println("[" + BOLD_RED + "2" + RESET + "] - " + BOLD_RED + "Não" + RESET);
                System.out.print("Deseja cadastrar mais uma turma? " + BOLD_CYAN);
                int continuar = Integer.parseInt(scanner.nextLine());
                if (continuar != 1) break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }

    private static void gerarHorario(Scanner scanner, GradeHorarioController controller, GerenciadorDeArquivos gerenciador) {
        while (true) {
            System.out.println(RESET + "\n=-=-=-=-=-= " + BOLD_GREEN + "Gerar Grade de Horário" + RESET + " =-=-=-=-=-=");
            System.out.print("Digite o nome da turma: " + BOLD_GREEN);
            String nome = scanner.nextLine();
            System.out.print(RESET + "Digite o ano da turma: " + BOLD_GREEN);
            int ano = Integer.parseInt(scanner.nextLine());
            try {
              //  gerenciador.gerarGradeHorario(controller, nome, ano);
                System.out.println(RESET + "Grade de horário gerada com sucesso!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }
}
