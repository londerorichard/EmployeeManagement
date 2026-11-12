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

    @Test
    void shouldCalculateTaxes() {

        FuncionarioPJ funcionarioPJ = new FuncionarioPJ(
                3,
                "Joao Silva",
                "529.982.247-25",
                2000.00,
                3
        );

        assertEquals(360.00, funcionarioPJ.calcularTributavel(), 0.001);
    }

    @Test
    void shouldCalculateRemunerationWithMultipleProjects() {
        FuncionarioPJ funcionario = new FuncionarioPJ(
                1, "Carlos Silva", "529.982.247-25",
                2500.00, 3
        );

        assertEquals(7500.00, funcionario.calcularRemuneracao(), 0.001);
    }

    @Test
    void shouldCalculateRemunerationWithOneProject() {
        FuncionarioPJ funcionario = new FuncionarioPJ(
                2, "Maria Silva", "111.444.777-35",
                2500.00, 1
        );

        assertEquals(2500.00, funcionario.calcularRemuneracao(), 0.001);
    }

    @Test
    void shouldCalculateRemunerationWithoutProjects() {
        FuncionarioPJ funcionario = new FuncionarioPJ(
                3, "Joao Silva", "529.982.247-25",
                2500.00, 0
        );

        assertEquals(0.00, funcionario.calcularRemuneracao(), 0.001);
    }
}