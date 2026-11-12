package repository;

import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioComissionado;
import model.FuncionarioPJ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exception.FuncionarioNaoEncontradoException;
import exception.FuncionarioJaCadastradoException;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioRepositoryTest {

    private FuncionarioRepository manager;

    @BeforeEach
    void setUp() {
        manager = new FuncionarioRepository();

        manager.addFuncionario(new FuncionarioCLT(
                1, "Maria Silva", "111.444.777-35",
                3000.00, new HashMap<>()
        ));

        manager.addFuncionario(new FuncionarioPJ(
                2, "Joao Silva", "529.982.247-25",
                2500.00, 3
        ));

        manager.addFuncionario(new FuncionarioComissionado(
                3, "Ana Souza", "935.411.347-80",
                2000.00, 10000.00, 10.0
        ));
    }

    // SEARCH BY ID

    @Test
    void shouldFindEmployeeById() {
        Funcionario funcionario = manager.buscarFuncionario(1);

        assertEquals(1, funcionario.getId());
        assertEquals("Maria Silva", funcionario.getNome());
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        assertThrows(
                FuncionarioNaoEncontradoException.class,
                () -> manager.buscarFuncionario(99)
        );
    }

    // SEARCH BY CPF

    @Test
    void shouldFindEmployeeByCPF() {
        Funcionario funcionario = manager.buscarFuncionario("529.982.247-25");

        assertEquals(2, funcionario.getId());
        assertEquals("Joao Silva", funcionario.getNome());
    }

    @Test
    void shouldThrowExceptionWhenCPFDoesNotExist() {
        assertThrows(
                FuncionarioNaoEncontradoException.class,
                () -> manager.buscarFuncionario("123.456.789-09")
        );
    }

    // SEARCH BY NAME

    @Test
    void shouldFindEmployeeByExactName() {
        List<Funcionario> funcionarios =
                manager.buscarFuncionario("Maria Silva", true);

        assertEquals(1, funcionarios.size());
        assertEquals("Maria Silva", funcionarios.get(0).getNome());
    }

    @Test
    void shouldFindEmployeesByPartialName() {
        List<Funcionario> funcionarios =
                manager.buscarFuncionario("Silva", false);

        assertEquals(2, funcionarios.size());
    }

    @Test
    void shouldIgnoreCaseWhenSearchingByName() {
        List<Funcionario> funcionarios =
                manager.buscarFuncionario("maria silva", true);

        assertEquals(1, funcionarios.size());
    }

    @Test
    void shouldReturnEmptyListWhenNameDoesNotExist() {
        List<Funcionario> funcionarios =
                manager.buscarFuncionario("Pedro", true);

        assertTrue(funcionarios.isEmpty());
    }
}