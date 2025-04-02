package gradehorario;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class identificacaoTest {
    private Materia materia;
    
    @Before
    public void setUp() {
        materia = new Materia(1, "Matematica");
    }
    
    // Verifica se o método getNome retorna o nome correto da matéria.
    @Test
    public void testGetNomeMateria() {
        assertEquals("Matematica", materia.getNome()); // Verifica se o nome inicial é "Matematica"

    }

    // Altera o nome da matéria para "Fisica" e verifica se a mudança foi bem-sucedida.
    @Test
    public void testSetNomeMateria() {
        materia.setNome("Fisica");
        assertEquals("Fisica", materia.getNome()); // Verifica se o nome foi alterado para "Fisica"
    }
    
    // Verifica se o método getId retorna o id correto da matéria.
    @Test
    public void testGetIdMateria() {
        assertEquals(Integer.valueOf(1), materia.getId()); // Verifica se o id inicial é 1
    }

    // Altera o id da matéria para 2 e verifica se a mudança foi bem-sucedida.
    @Test
    public void testSetIdMateria() {
        materia.setId(2);
        assertEquals(Integer.valueOf(2), materia.getId()); // Verifica se o id foi alterado para 2
    }
}
