package gradehorario;

import java.util.*;

public class GradeHorarioController {
    private Map<Integer, Professor> professores;
    private Map<Integer, Turma> turmas;
    private Map<Integer, Materia> materias;
    private Random random;

    public GradeHorarioController() {
        professores = new HashMap<>();
        turmas = new HashMap<>();
        materias = new HashMap<>();
        random = new Random();

        cadastrarProfessor(200, "João");
        cadastrarProfessor(201, "Antônio");
        cadastrarProfessor(202, "Raissa");

        cadastrarMateria(200, "Ed. Física");
        cadastrarMateria(201, "História");
        cadastrarMateria(202, "Ciências");

        adicionarProfessorMateria(200, 200); // Ed. Física - João
        adicionarProfessorMateria(201, 201); // História - Antônio
        adicionarProfessorMateria(202, 202); // Ciências - Raissa
    }

    public String cadastrarProfessor(int id, String nome) {
        if (professores.containsKey(id)) {
            return "Já existe um professor cadastrado com este ID.";
        } else {
            Professor professor = new Professor(id, nome);
            professores.put(id, professor);
            return "Professor cadastrado com sucesso.";
        }
    }

    public String cadastrarMateria(int id, String nome) {
        if (materias.containsKey(id)) {
            return "Já existe uma matéria cadastrada com este ID.";
        } else {
            Materia materia = new Materia(id, nome);
            materias.put(id, materia);
            return "Matéria cadastrada com sucesso.";
        }
    }

    public String adicionarProfessorMateria(int idMateria, int idProfessor) {
        if (!materias.containsKey(idMateria)) {
            return "Matéria não encontrada.";
        }
        if (!professores.containsKey(idProfessor)) {
            return "Professor não encontrado.";
        }
        Materia materia = materias.get(idMateria);
        Professor professor = professores.get(idProfessor);
        materia.setProfessor(professor);
        return "Professor associado à matéria com sucesso.";
    }

    public Turma getTurma(int idTurma) {
        return turmas.get(idTurma);
    }

    public boolean criarMateria(Integer id, String nome) {
        validar(id, nome);
        if (!materias.containsKey(id)) {
            Materia materia = new Materia(id, nome);
            materias.put(id, materia);
            return true;
        }
        throw new IllegalArgumentException("ID INVÁLIDO");
    }

    public String adicionarProfessor(Integer id, String nome) {
        if (!professores.containsKey(id)) {
            Professor professor = new Professor(id, nome);
            professores.put(id, professor);
            return "PROFESSOR ADICIONADO COM SUCESSO";
        }
        throw new IllegalArgumentException("ID INVÁLIDO");
    }

    public void criarPlanilhasExcelParaTurmas() {
        String path = System.getProperty("user.home") + "/Desktop/Horários das Turmas";
        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos(path);

        Collection<Turma> turmaValues = getTurmas().values();
        List<Turma> turmas = new ArrayList<>(turmaValues);

        if (turmas != null) {
            for (Turma turma : turmas) {
                gerenciador.criarPlanilhaExcel(turma);
            }
        } else {
            System.out.println("Nenhuma turma encontrada para criar planilhas.");
        }
    }

    public String criarTurma(int id, String nome, List<Integer> materiasIds) {
        validar(id, nome);
        if (!turmas.containsKey(id)) {
            Turma turma = new Turma(id, nome); // Removido o parâmetro emailTurma
            for (Integer idMateria : materiasIds) {
                if (materias.containsKey(idMateria)) {
                    Materia materia = materias.get(idMateria);
                    turma.adicionarMateria(materia);
                } else {
                    throw new IllegalArgumentException("ID de matéria inválido: " + idMateria);
                }
            }
            turmas.put(id, turma);
            return "TURMA CRIADA COM SUCESSO";
        }
        throw new IllegalArgumentException("ID INVÁLIDO");
    }

    public void adicionarProfessorNaMateria(Integer idProfessor, Integer idMateria) {
        Professor professor = professores.get(idProfessor);
        Materia materia = materias.get(idMateria);
        if (professor.getMaterias().contains(materia)) {
            throw new IllegalArgumentException("Professor já ensina essa matéria");
        }
        materia.setProfessor(professor);
        professor.adicionarMateria(materia);
    }

    public void adicionarMateriaNaTurma(Integer idMateria, Integer idTurma) {
        Turma turma = turmas.get(idTurma);
        Materia materia = materias.get(idMateria);
        turma.adicionarMateria(materia);
    }

    public void gerarGradeHorario(int idTurma) {
        Turma turma = turmas.get(idTurma);
        if (turma == null) {
            throw new IllegalArgumentException("Turma não encontrada.");
        }
        for (int i = 0; i < 25; i++) {
            List<Materia> materiasTurma = new ArrayList<>(turma.getMaterias());
            boolean materiaAdicionada = false;
            while (!materiaAdicionada && materiasTurma.size() > 0) {
                int posSorteada = random.nextInt(materiasTurma.size());
                Materia materia = materiasTurma.get(posSorteada);
                // Verifica se o countDia está dentro do intervalo correto antes de adicionar a matéria
                if (turma.getCountDia() <= 5) {
                    materiaAdicionada = turma.adicionarMateriaNaGrade(materia);
                }
                if (materiaAdicionada) {
                    materiasTurma.remove(materia); // Remove a matéria da lista após ser adicionada
                }
            }
        }
    }

    public void validar(Integer id, String nome) {
        if (id < 0 || id > 1000 || nome.isEmpty()) {
            throw new IllegalArgumentException("ERRO DE VALIDAÇÃO");
        }
    }

    public Map<Integer, Professor> getProfessores() {
        return professores;
    }

    public Map<Integer, Materia> getMaterias() {
        return materias;
    }

    public Map<Integer, Turma> getTurmas() {
        return turmas;
    }


	public Turma getTurmaByName(String nomeTurma) {
    for (Turma turma : turmas.values()) {
        if (turma.getNome().equals(nomeTurma)) {
            return turma;
        }
    }
    return null; // Retorna null se a turma não for encontrada
}

	public void criarTurma(String nome, int parseInt, List<Integer> collect) {
		// TODO Auto-generated method stub
		
	}
}
