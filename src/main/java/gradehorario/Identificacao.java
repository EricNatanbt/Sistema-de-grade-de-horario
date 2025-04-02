package gradehorario;

// Classe abstrata Identificacao que será a base para outras classes
public abstract class Identificacao {
    // Atributos privados para armazenar o nome e o id
    private String nome;
    private Integer id;

    // Construtor que inicializa os atributos nome e id
    public Identificacao(Integer id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    // Método getter para obter o nome
    public String getNome() {
        return nome;
    }

    // Método setter para definir o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método getter para obter o id
    public Integer getId() {
        return id;
    }

    // Método setter para definir o id
    public void setId(Integer id) {
        this.id = id;
    }
}
