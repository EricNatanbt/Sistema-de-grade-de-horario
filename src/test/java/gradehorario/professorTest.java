package gradehorario;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class professorTest {
    private Materia materia;
    private Professor professor;
    
    @Before
    public void setUp() {
        materia = new Materia(1, "Matematica");
        professor = new Professor(1, "Prof. Silva");
    }

    // Adiciona a matéria 'materia' ao professor.
    // Verifica se a lista de matérias do professor contém a matéria adicionada
    // e se o professor da matéria é o professor atual.
    @Test
    public void testAdicionarMateria() {
        professor.adicionarMateria(materia);
        List<Materia> materias = professor.getMaterias();

        assertTrue(materias.contains(materia)); // Verifica se a lista de matérias do professor contém 'materia'
        assertEquals(professor, materia.getProfessor()); // Verifica se o professor da matéria é o professor atual
    }
    
    // Adiciona a matéria 'materia' ao professor.
    // Verifica se a lista de matérias do professor contém exatamente uma matéria
    // e se essa matéria é 'materia'.
    @Test
    public void testGetMateriasProfessor() {
        professor.adicionarMateria(materia);
        List<Materia> materias = professor.getMaterias();

        assertEquals(1, materias.size()); // Verifica se a lista de matérias do professor contém exatamente 1 matéria
        assertEquals(materia, materias.get(0)); // Verifica se a única matéria na lista é 'materia'
    }

    // Verifica se o horário (1, 1) está disponível para o professor.
    @Test
    public void testGetHorarioDisponivelProfessor() {
        assertTrue(professor.getHorarioDisponivel(1, 1)); // Verifica se o horário (1, 1) está disponível
    }
    
    // Define o horário (1, 1) como indisponível para o professor
    // e verifica se a mudança foi bem-sucedida.
    @Test
    public void testSetHorarioIndisponivelProfessor() {
        professor.setHorarioDisponivel(1, 1);
        assertFalse(professor.getHorarioDisponivel(1, 1));// Verifica se o horário (1, 1) foi marcado como indisponível
    }
}
