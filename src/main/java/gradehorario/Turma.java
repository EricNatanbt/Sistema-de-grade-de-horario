// Alterações em Turma.java
package gradehorario;

import java.util.ArrayList;
import java.util.List;

// A classe Turma herda de Identificacao, que contém atributos comuns como id e nome.
public class Turma extends Identificacao {
    // Constantes para resetar cor e definir cor em negrito ciano.
    private static final String RESET = "\033[0m";     
    private static final String BOLD_CYAN = "\033[1;36m";

    // Variáveis para rastrear o dia e horário atuais.
    private int countDia;
    private int countHorario;

    // Arrays para armazenar as matérias de cada dia da semana.
    private Materia[] segunda;
    private Materia[] terca;
    private Materia[] quarta;
    private Materia[] quinta;
    private Materia[] sexta;

    // Lista de matérias associadas à turma.
    private List<Materia> materias;
    private String email;

    // Construtor para inicializar a turma com id, nome e email.
    public Turma(Integer id, String nome) {
        super(id, nome);
        this.countDia = 1;
        this.countHorario = 0;
        this.materias = new ArrayList<>();

        // Inicializando arrays para os dias da semana.
        segunda = new Materia[5];
        terca = new Materia[5];
        quarta = new Materia[5];
        quinta = new Materia[5];
        sexta = new Materia[5];

        // Inicializar os arrays com instâncias de Materia indicando disponibilidade.
        for (int i = 0; i < 5; i++) {
            segunda[i] = new Materia(0, "Disponível");
            terca[i] = new Materia(0, "Disponível");
            quarta[i] = new Materia(0, "Disponível");
            quinta[i] = new Materia(0, "Disponível");
            sexta[i] = new Materia(0, "Disponível");
        }
    }

    // Método para obter o email da turma.
    public String getEmail() {
        return email;
    }

    // Método para adicionar uma matéria à lista de matérias da turma.
    public void adicionarMateria(Materia materia){
    	materias.add(materia);
    	}
 // Método para adicionar uma matéria à grade de horário da turma.
    public boolean adicionarMateriaNaGrade(Materia materia){
        if (countDia > 5) {
            return false; // Retorna false se o dia for maior que 5
        }

        Materia[] diaDaSemana = getPosicaoDia(countDia);
        
        // Verifica se o professor está disponível no horário e dia atuais.
        if(!materia.getProfessor().getHorarioDisponivel(countDia, countHorario)){
            return false;
        }

        // Marca o horário do professor como indisponível e adiciona a matéria ao horário.
        materia.getProfessor().setHorarioDisponivel(countDia, countHorario);
        diaDaSemana[countHorario] = materia;
        countHorario++;

        // Avança para o próximo dia se cinco horários forem preenchidos.
        if(countHorario == 5){
            countHorario = 0;
            countDia++;
        }
        return true;
    }

    public int getCountDia() {
        return countDia;
    }




    // Método para imprimir o horário de um dia específico.
    public String imprimirHorarioDia(int dia){
        Materia[] diaDaSemana = getPosicaoDia(dia);
        String result = getPosicaoDiaStr(dia);
        for(int i = 0; i < 5; i++){
            result += System.lineSeparator() + " " + diaDaSemana[i].getNome() + " - Prof. " + diaDaSemana[i].getProfessor().getNome();
        }
        return result;
    }

    // Método para obter a lista de matérias da turma.
    public List<Materia> getMaterias() {
        return materias;
    }

    // Método para obter o nome do dia da semana em negrito.
    private String getPosicaoDiaStr(int dia){
        switch (dia){
            case 1:
                return BOLD_CYAN + "\nSEGUNDA" + RESET;
            case 2:
                return BOLD_CYAN + "\nTERÇA" + RESET;
            case 3:
                return BOLD_CYAN + "\nQUARTA" + RESET;
            case 4:
                return BOLD_CYAN + "\nQUINTA" + RESET;
            case 5:
                return BOLD_CYAN + "\nSEXTA" + RESET;
            default:
                throw new IllegalArgumentException();
        }
    }

    // Método para obter o array de matérias de um dia específico.
    public Materia[] getPosicaoDia(int dia) {
        switch (dia) {
            case 1:
                return segunda;
            case 2:
                return terca;
            case 3:
                return quarta;
            case 4:
                return quinta;
            case 5:
                return sexta;
            default:
                throw new IllegalArgumentException("Dia inválido");
        }
    }
    public String imprimirHorario() {
        StringBuilder horario = new StringBuilder();
        horario.append("Horário da Turma ").append(this.getNome()).append(":\n");
        horario.append("Segunda-feira:\n");
        horario.append(imprimirHorarioDia(1)).append("\n");
        horario.append("Terça-feira:\n");
        horario.append(imprimirHorarioDia(2)).append("\n");
        horario.append("Quarta-feira:\n");
        horario.append(imprimirHorarioDia(3)).append("\n");
        horario.append("Quinta-feira:\n");
        horario.append(imprimirHorarioDia(4)).append("\n");
        horario.append("Sexta-feira:\n");
        horario.append(imprimirHorarioDia(5)).append("\n");
        return horario.toString();
    }
}