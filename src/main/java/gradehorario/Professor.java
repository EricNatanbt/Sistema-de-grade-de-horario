package gradehorario;

import java.util.ArrayList;
import java.util.List;

// A classe Professor herda de Identificacao, representando um professor com id, nome, matérias e horários disponíveis
public class Professor extends Identificacao {
    private List<Materia> materias; // Lista de matérias que o professor ensina
    private Horario[] horarios;     // Array de Horarios representando a disponibilidade do professor durante a semana

    // Construtor que inicializa o professor com um id, nome e configura os horários e a lista de matérias
    public Professor(Integer id, String nome) {
        super(id, nome); // Chama o construtor da superclasse Identificacao para inicializar id e nome
        this.horarios = new Horario[5]; // Inicializa o array de horários com 5 dias da semana
        materias = new ArrayList<Materia>(); // Inicializa a lista de matérias
        for (int i = 0; i < 5; i++) {
            this.horarios[i] = new Horario(); // Inicializa cada dia da semana com um objeto Horario
        }
    }

    // Método para adicionar uma matéria ao professor
    public void adicionarMateria(Materia newMateria) {
        if (newMateria != null) {
            materias.add(newMateria); // Adiciona a nova matéria à lista de matérias
            newMateria.setProfessor(this); // Define o professor da nova matéria como este professor
        }
    }

    // Método para exibir os horários disponíveis do professor
    public void exibindoHorarios() {
        for (int i = 0; i < 5; i++) {
            String dia;
            switch (i) {
                case 0:
                    dia = "SEGUNDA";
                    break;
                case 1:
                    dia = "TERÇA";
                    break;
                case 2:
                    dia = "QUARTA";
                    break;
                case 3:
                    dia = "QUINTA";
                    break;
                default:
                    dia = "SEXTA";
                    break;
            }

            System.out.println("Horários na " + dia + ": ");
            for (int j = 1; j < 6; j++) {
                String disponibilidade = horarios[i].getHorarioDisponivel(j) ? "Disponível" : "OCUPADO";
                System.out.println(j + "º horário: " + disponibilidade);
            }
        }
    }

    // Método para exibir as matérias que o professor ensina
    public void exibirMaterias() {
        System.out.println("O professor " + getNome() + " ensina: ");
        for (Materia materia : materias) {
            System.out.println(materia.getNome());
        }
    }

    // Método para verificar se o professor está disponível em um determinado dia e horário
    public boolean getHorarioDisponivel(int dia, int horario) {
        return horarios[dia - 1].getHorarioDisponivel(horario + 1);
    }

    // Método para definir a disponibilidade do professor em um determinado dia e horário
    public void setHorarioDisponivel(Integer dia, int horario) {
        horarios[dia - 1].setHorarioDisponivel(horario + 1);
    }

    // Método para obter a lista de matérias que o professor ensina
    public List<Materia> getMaterias() {
        return materias;
    }
}
