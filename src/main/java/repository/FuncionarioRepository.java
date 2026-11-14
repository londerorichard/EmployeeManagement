package repository;

import exception.FuncionarioNaoEncontradoException;
import exception.FuncionarioJaCadastradoException;
import model.Funcionario;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class FuncionarioRepository {

    private final Map<Integer, Funcionario> funcionarios;

    public FuncionarioRepository() {
        this.funcionarios = new HashMap<>();
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios.values());
    }

    public void addFuncionario(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }

        if (funcionarios.containsKey(funcionario.getId())) {
            throw new FuncionarioJaCadastradoException(
                    "Employee ID already registered."
            );
        }

        funcionarios.put(funcionario.getId(), funcionario);
    }

    public void removerFuncionario(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID.");
        }

        if (!funcionarios.containsKey(id)) {
            throw new FuncionarioNaoEncontradoException(
                    "Employee not found."
            );
        }

        funcionarios.remove(id);
    }

    public Funcionario buscarFuncionario(int id) {

        Funcionario funcionario = funcionarios.get(id);

        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(
                    "Employee not found with ID: " + id
            );
        }

        return funcionario;
    }

    public Funcionario buscarFuncionario(String cpf) {

        return funcionarios.values()
                .stream()
                .filter(funcionario -> funcionario.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(
                        "Employee not found with CPF: " + cpf
                ));
    }

    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {

        return funcionarios.values()
                .stream()
                .filter(funcionario -> {
                    if (buscaExata) {
                        return funcionario.getNome().equalsIgnoreCase(nome);
                    }

                    return funcionario.getNome().toLowerCase()
                            .contains(nome.toLowerCase());
                })
                .toList();
    }
}