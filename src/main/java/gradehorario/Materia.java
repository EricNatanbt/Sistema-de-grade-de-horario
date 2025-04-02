package gradehorario;

// A classe Materia herda de Identificacao, representando uma matéria com um id e nome, e associada a um professor
public class Materia extends Identificacao {
    private Professor professor; // Atributo que representa o professor associado a esta matéria

    // Construtor que inicializa a matéria com um id e nome, utilizando o construtor da superclasse Identificacao
    public Materia(Integer id, String nome) {
        super(id, nome);
    }

    // Método getter para obter o professor associado a esta matéria
    public Professor getProfessor() {
        return professor;
    }

    // Método setter para associar um professor a esta matéria
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
