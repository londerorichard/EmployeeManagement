package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioPJTest {

    @Test
    void shouldCreatePJEmployeeWithValidInformation() {

        FuncionarioPJ funcionario = new FuncionarioPJ(
                3,
                "Carlos Silva",
                "529.982.247-25",
                1500.00,
                4
        );

        assertEquals(3, funcionario.getId());
        assertEquals("Carlos Silva", funcionario.getNome());
        assertEquals("529.982.247-25", funcionario.getCpf());
        assertEquals(1500.00, funcionario.getValorProjeto(), 0.001);
        assertEquals(4, funcionario.getQuantidadeProjetos());
    }
}