package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioComissionadoTest {

    @Test
    void shouldCreateCommissionedEmployeeWithValidInformation() {

        FuncionarioComissionado funcionario = new FuncionarioComissionado(
                4,
                "Pedro Santos",
                "529.982.247-25",
                2500.00,
                10000.00,
                10.0
        );

        assertEquals(4, funcionario.getId());
        assertEquals("Pedro Santos", funcionario.getNome());
        assertEquals("529.982.247-25", funcionario.getCpf());
        assertEquals(2500.00, funcionario.getSalarioBase(), 0.001);
        assertEquals(10000.00, funcionario.getVendas(), 0.001);
        assertEquals(10.0, funcionario.getPercentualComissao(), 0.001);
    }
}