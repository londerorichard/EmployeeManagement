package service;

import model.Funcionario;
import repository.FuncionarioRepository;

import java.util.List;

public class FolhaPagamentoService {

    private final FuncionarioRepository repository;

    public FolhaPagamentoService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public double calcularFolhaPagamento() {
        List<Funcionario> funcionarios = repository.listarFuncionarios();

        return funcionarios.stream()
                .mapToDouble(Funcionario::calcularRemuneracao)
                .sum();
    }

    public String gerarRelatorio() {
        List<Funcionario> funcionarios = repository.listarFuncionarios();

        if (funcionarios.isEmpty()) {
            return "No employees registered.";
        }

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("===== PAYROLL REPORT =====\n");

        for (Funcionario funcionario : funcionarios) {
            relatorio.append("ID: ").append(funcionario.getId()).append("\n");
            relatorio.append("Name: ").append(funcionario.getNome()).append("\n");
            relatorio.append("CPF: ").append(funcionario.getCpf()).append("\n");
            relatorio.append("Remuneration: ")
                    .append(funcionario.calcularRemuneracao())
                    .append("\n");
            relatorio.append("--------------------------\n");
        }

        relatorio.append("Total payroll: ")
                .append(calcularFolhaPagamento());

        return relatorio.toString();
    }
}