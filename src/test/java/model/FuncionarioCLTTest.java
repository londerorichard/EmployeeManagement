package model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FuncionarioCLTTest {

    @Test
    void shouldCreateCLTEmployeeWithValidInformation() {

        HashMap<String, Double> beneficios = new HashMap<>();
        beneficios.put("Vale refeicao", 500.00);
        beneficios.put("Vale transporte", 200.00);

        FuncionarioCLT funcionarioCLT = new FuncionarioCLT(
                1,
                "Joao Silva",
                "529.982.247-25",
                3000.00,
                beneficios
        );

        assertEquals(1, funcionarioCLT.getId());
        assertEquals("Joao Silva", funcionarioCLT.getNome());
        assertEquals("529.982.247-25", funcionarioCLT.getCpf());
        assertEquals(3000.00, funcionarioCLT.getSalarioBase(), 0.001);
        assertEquals(2, funcionarioCLT.getBeneficios().size());
        assertEquals(500.00, funcionarioCLT.getBeneficios().get("Vale refeicao"));
    }

    @Test
    void shouldCreateCLTEmployeeWithoutBenefits() {

        FuncionarioCLT funcionarioCLT = new FuncionarioCLT(
                2,
                "Maria Silva",
                "111.444.777-35",
                3500.00,
                null
        );

        assertTrue(funcionarioCLT.getBeneficios().isEmpty());
    }

    @Test
    void shouldCalculateTaxes() {

        FuncionarioCLT funcionarioCLT = new FuncionarioCLT(
                2,
                "Maria Silva",
                "111.444.777-35",
                3500.00,
                null
        );

        funcionarioCLT.calcularTributavel();

        assertEquals(350.00, funcionarioCLT.calcularTributavel(), 0.001);
    }
}