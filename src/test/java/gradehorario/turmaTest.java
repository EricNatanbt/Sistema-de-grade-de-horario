package gradehorario;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class turmaTest {
    private Materia materia;
    private Professor professor;
    private Turma turma;
    
    @Before
    public void setUp() {
        materia = new Materia(1, "Matematica");
        professor = new Professor(1, "Prof. Silva");
        turma = new Turma(1, "Turma 1");
    }

    // Adiciona a matéria 'materia' à turma.
    // Verifica se a lista de matérias da turma contém a matéria adicionada.
    @Test
    public void testAdicionarMateriaTurma() {
        turma.adicionarMateria(materia);
        List<Materia> materias = turma.getMaterias();
        assertTrue(materias.contains(materia)); // Verifica se a lista de matérias da turma contém 'materia'
    }

    // Adiciona a matéria 'materia' ao professor.
    // Adiciona a matéria 'materia' à grade da turma e verifica se a adição foi bem-sucedida.
    // Verifica se o horário (1, 0) do professor foi marcado como indisponível.
    @Test
    public void testAdicionarMateriaNaGrade() {
        professor.adicionarMateria(materia);
        boolean resultado = turma.adicionarMateriaNaGrade(materia);
        assertTrue(resultado);  // Verifica se a matéria foi adicionada com sucesso à grade da turma
        assertFalse(professor.getHorarioDisponivel(1, 0)); // Verifica se o horário (1, 0) do professor está indisponível
    }
    
}
