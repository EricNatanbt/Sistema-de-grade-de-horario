package gradehorario;

// Classe Horario para gerenciar a disponibilidade de horários
public class Horario {
    // Atributos booleanos para representar a disponibilidade dos horários
    private boolean primeiroHorario;
    private boolean segundoHorario;
    private boolean terceiroHorario;
    private boolean quartoHorario;
    private boolean quintoHorario;

    // Construtor que inicializa todos os horários como disponíveis (true)
    public Horario() {
        this.primeiroHorario = true;
        this.segundoHorario = true;
        this.terceiroHorario = true;
        this.quartoHorario = true;
        this.quintoHorario = true;
    }

    // Método para obter a disponibilidade de um horário específico
    public boolean getHorarioDisponivel(int posHorario) {
        switch (posHorario) {
            case 1:
                return primeiroHorario;
            case 2:
                return segundoHorario;
            case 3:
                return terceiroHorario;
            case 4:
                return quartoHorario;
            case 5:
                return quintoHorario;
            default:
                throw new ArrayIndexOutOfBoundsException(); // Lança exceção se o horário for inválido
        }
    }

    // Método para alternar a disponibilidade de um horário específico
    public void setHorarioDisponivel(int posHorario) {
        switch (posHorario) {
            case 1:
                primeiroHorario = !primeiroHorario; // Alterna a disponibilidade do primeiro horário
                break;
            case 2:
                segundoHorario = !segundoHorario; // Alterna a disponibilidade do segundo horário
                break;
            case 3:
                terceiroHorario = !terceiroHorario; // Alterna a disponibilidade do terceiro horário
                break;
            case 4:
                quartoHorario = !quartoHorario; // Alterna a disponibilidade do quarto horário
                break;
            case 5:
                quintoHorario = !quintoHorario; // Alterna a disponibilidade do quinto horário
                break;
            default:
                throw new ArrayIndexOutOfBoundsException(); // Lança exceção se o horário for inválido
        }
    }
}
