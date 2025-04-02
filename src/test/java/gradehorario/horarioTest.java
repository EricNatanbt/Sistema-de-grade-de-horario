package gradehorario;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class horarioTest {
    private Horario horario;
    
    
    // Este método é executado antes de cada teste.
    // Ele inicializa a instância de 'Horario'.
    @Before
    public void setUp() {
        horario = new Horario();
    }
    
    // Verifica se os horários de 1 a 5 estão inicialmente disponíveis.
    @Test
    public void testGetHorarioDisponivel() {
        assertTrue(horario.getHorarioDisponivel(1)); // Verifica se o horário 1 está disponível
        assertTrue(horario.getHorarioDisponivel(2)); // Verifica se o horário 2 está disponível
        assertTrue(horario.getHorarioDisponivel(3)); // Verifica se o horário 3 está disponível
        assertTrue(horario.getHorarioDisponivel(4)); // Verifica se o horário 4 está disponível
        assertTrue(horario.getHorarioDisponivel(5)); // Verifica se o horário 5 está disponível
    }
    
    // Define os horários de 1 a 5 como não disponíveis e verifica se a mudança foi bem-sucedida.
    @Test
    public void testSetHorarioDisponivel() {
        horario.setHorarioDisponivel(1); 
        assertFalse(horario.getHorarioDisponivel(1)); // Verifica se o horário 1 foi marcado como não disponível

        horario.setHorarioDisponivel(2);
        assertFalse(horario.getHorarioDisponivel(2)); // Verifica se o horário 2 foi marcado como não disponível

        horario.setHorarioDisponivel(3);
        assertFalse(horario.getHorarioDisponivel(3)); // Verifica se o horário 3 foi marcado como não disponível

        horario.setHorarioDisponivel(4);
        assertFalse(horario.getHorarioDisponivel(4)); // Verifica se o horário 4 foi marcado como não disponível

        horario.setHorarioDisponivel(5);
        assertFalse(horario.getHorarioDisponivel(5)); // Verifica se o horário 5 foi marcado como não disponível
    }
}
