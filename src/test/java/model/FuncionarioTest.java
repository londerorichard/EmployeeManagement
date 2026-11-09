package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    void shouldCreateEmployeeWithValidInformation() {

        FuncionarioCLT funcionario = new FuncionarioCLT(
                1,
                "Joao Silva",
                "529.982.247-25",
                3000.00,
                null
        );

        assertEquals(1, funcionario.getId());
        assertEquals("Joao Silva", funcionario.getNome());
        assertEquals("529.982.247-25", funcionario.getCpf());
        assertEquals(3000.00, funcionario.getSalarioBase(), 0.001);
    }

    @Test
    void shouldRejectNegativeId() {

        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        -1,
                        "Joao Silva",
                        "529.982.247-25",
                        3000.00,
                        null
                )
        );
    }

    @Test
    void shouldRejectEmptyName() {

        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        1,
                        "",
                        "529.982.247-25",
                        3000.00,
                        null
                )
        );
    }

    @Test
    void shouldRejectNegativeSalary() {

        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        1,
                        "Joao Silva",
                        "529.982.247-25",
                        -3000.00,
                        null
                )
        );
    }

    @Test
    void shouldRejectInvalidCPF() {

        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        1,
                        "Joao Silva",
                        "123.456.789-00",
                        3000.00,
                        null
                )
        );
    }

    @Test
    void shouldRejectNullName() {

        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        1,
                        null,
                        "529.982.247-25",
                        3000.00,
                        null
                )
        );
    }

    @Test
    void shouldRejectNullCPF() {

        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        1,
                        "Joao Silva",
                        null,
                        3000.00,
                        null
                )
        );
    }
}