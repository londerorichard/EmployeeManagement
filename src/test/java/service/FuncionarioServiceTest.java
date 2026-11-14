package service;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import model.FuncionarioCLT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.FuncionarioRepository;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {

    private FuncionarioRepository repository;
    private FuncionarioService service;
    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        repository = new FuncionarioRepository();
        service = new FuncionarioService(repository);

        funcionario = new FuncionarioCLT(
                1,
                "John Smith",
                "529.982.247-25",
                3000.0,
                new HashMap<>()
        );
    }

    // CADASTRAR FUNCIONARIO

    @Test
    void shouldRegisterEmployee() {
        service.cadastrarFuncionario(funcionario);

        assertEquals(funcionario, repository.buscarFuncionario(1));
    }

    @Test
    void shouldNotRegisterNullEmployee() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrarFuncionario(null)
        );
    }

    @Test
    void shouldNotRegisterEmployeeWithDuplicateId() {
        service.cadastrarFuncionario(funcionario);

        Funcionario duplicate = new FuncionarioCLT(
                1,
                "Jane Smith",
                "111.444.777-35",
                4000.0,
                new HashMap<>()
        );

        assertThrows(
                FuncionarioJaCadastradoException.class,
                () -> service.cadastrarFuncionario(duplicate)
        );
    }

    @Test
    void shouldNotRegisterEmployeeWithDuplicateCpf() {
        service.cadastrarFuncionario(funcionario);

        Funcionario duplicate = new FuncionarioCLT(
                2,
                "Jane Smith",
                "529.982.247-25",
                4000.0,
                new HashMap<>()
        );

        assertThrows(
                FuncionarioJaCadastradoException.class,
                () -> service.cadastrarFuncionario(duplicate)
        );
    }

    // LISTAR FUNCIONARIOS

    @Test
    void shouldListRegisteredEmployees() {
        service.cadastrarFuncionario(funcionario);

        List<Funcionario> result = service.listarFuncionarios();

        assertEquals(1, result.size());
        assertEquals(funcionario, result.get(0));
    }

    @Test
    void shouldThrowExceptionWhenListingEmptyRepository() {
        assertThrows(
                FuncionarioNaoEncontradoException.class,
                () -> service.listarFuncionarios()
        );
    }

    // REMOVER FUNCIONARIO

    @Test
    void shouldRemoveEmployee() {
        service.cadastrarFuncionario(funcionario);

        service.removerFuncionario(1);

        assertTrue(repository.listarFuncionarios().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonexistentEmployee() {
        assertThrows(
                FuncionarioNaoEncontradoException.class,
                () -> service.removerFuncionario(99)
        );
    }
}