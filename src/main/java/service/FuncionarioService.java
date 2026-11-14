package service;

import exception.FuncionarioNaoEncontradoException;
import exception.FuncionarioJaCadastradoException;
import model.Funcionario;
import repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }

        try {
            repository.buscarFuncionario(funcionario.getId());

            throw new FuncionarioJaCadastradoException(
                    "An employee with this ID is already registered."
            );

        } catch (FuncionarioNaoEncontradoException ignored) {
            // ID is available.
        }

        try {
            repository.buscarFuncionario(funcionario.getCpf());

            throw new FuncionarioJaCadastradoException(
                    "An employee with this CPF is already registered."
            );

        } catch (FuncionarioNaoEncontradoException ignored) {
            // CPF is available.
        }

        repository.addFuncionario(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = repository.listarFuncionarios();

        if (funcionarios.isEmpty()) {
            throw new FuncionarioNaoEncontradoException(
                    "No employees found."
            );
        }

        return funcionarios;
    }

    public void removerFuncionario(int id) {
        if (repository.buscarFuncionario(id) == null) {
            throw new FuncionarioNaoEncontradoException(
                    "Employee not found."
            );
        }

        repository.removerFuncionario(id);
    }
}


