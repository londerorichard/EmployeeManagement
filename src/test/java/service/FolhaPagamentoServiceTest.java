package service;

import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioPJ;
import model.FuncionarioComissionado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.FuncionarioRepository;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FolhaPagamentoServiceTest {

    private FuncionarioRepository repository;
    private FolhaPagamentoService service;

    @BeforeEach
    void setUp() {
        repository = new FuncionarioRepository();
        service = new FolhaPagamentoService(repository);
    }

    // CALCULAR FOLHA DE PAGAMENTO

    @Test
    void shouldCalculateTotalPayroll() {
        Funcionario clt = new FuncionarioCLT(
                1, "John", "529.982.247-25",
                3000.0, new HashMap<>()
        );

        Funcionario pj = new FuncionarioPJ(
                2, "Mary", "111.444.777-35",
                2000.0, 2
        );

        Funcionario comissionado = new FuncionarioComissionado(
                3, "Peter", "935.411.347-80",
                1500.0, 10000.0, 10.0
        );

        repository.addFuncionario(clt);
        repository.addFuncionario(pj);
        repository.addFuncionario(comissionado);

        double total = service.calcularFolhaPagamento();

        double expected = clt.calcularRemuneracao()
                + pj.calcularRemuneracao()
                + comissionado.calcularRemuneracao();

        assertEquals(expected, total, 0.001);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoEmployees() {
        assertEquals(0.0, service.calcularFolhaPagamento(), 0.001);
    }

    // GERAR RELATORIO

    @Test
    void shouldGenerateReportWithEmployeeInformation() {
        Funcionario funcionario = new FuncionarioCLT(
                1, "John", "529.982.247-25",
                3000.0, new HashMap<>()
        );

        repository.addFuncionario(funcionario);

        String report = service.gerarRelatorio();

        assertTrue(report.contains("PAYROLL REPORT"));
        assertTrue(report.contains("John"));
        assertTrue(report.contains("529.982.247-25"));
        assertTrue(report.contains("Total payroll:"));
    }

    @Test
    void shouldIncludeAllEmployeesInReport() {
        Funcionario funcionario1 = new FuncionarioCLT(
                1, "John", "529.982.247-25",
                3000.0, new HashMap<>()
        );

        Funcionario funcionario2 = new FuncionarioCLT(
                2, "Mary", "111.444.777-35",
                4000.0, new HashMap<>()
        );

        repository.addFuncionario(funcionario1);
        repository.addFuncionario(funcionario2);

        String report = service.gerarRelatorio();

        assertTrue(report.contains("John"));
        assertTrue(report.contains("Mary"));
    }

    @Test
    void shouldReturnMessageWhenThereAreNoEmployees() {
        String report = service.gerarRelatorio();

        assertEquals("No employees registered.", report);
    }
}