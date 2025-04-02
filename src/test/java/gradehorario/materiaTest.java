package gradehorario;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class materiaTest {
    private Materia materia;
    private Professor professor;
    
    // Este método é executado antes de cada teste.
    // Ele inicializa as instâncias de 'Materia' com id 1 e nome "Matematica"
    // e 'Professor' com id 1 e nome "Prof. Silva".
    @Before
    public void setUp() {
        materia = new Materia(1, "Matematica");
        professor = new Professor(1, "Prof. Silva");
    }

    // Verifica se o professor da matéria é inicialmente nulo.
    @Test
    public void testGetProfessor() {
        assertNull(materia.getProfessor()); // Verifica se o professor inicial é nulo
    }

    // Define o professor da matéria como 'professor' e verifica se a mudança foi bem-sucedida.
    @Test
    public void testSetProfessor() {
        materia.setProfessor(professor);
        assertEquals(professor, materia.getProfessor()); // Verifica se o professor foi alterado corretamente
    }
}
