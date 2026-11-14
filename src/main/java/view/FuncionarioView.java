package view;

import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioPJ;
import model.FuncionarioComissionado;
import service.FuncionarioService;
import service.FolhaPagamentoService;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FuncionarioView {

    private final FuncionarioService funcionarioService;
    private final FolhaPagamentoService folhaPagamentoService;
    private final Scanner scanner;

    public FuncionarioView(
            FuncionarioService funcionarioService,
            FolhaPagamentoService folhaPagamentoService) {

        this.funcionarioService = funcionarioService;
        this.folhaPagamentoService = folhaPagamentoService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Choose an option: ");

            try {
                switch (opcao) {
                    case 1:
                        cadastrarFuncionario();
                        break;
                    case 2:
                        listarFuncionarios();
                        break;
                    case 3:
                        removerFuncionario();
                        break;
                    case 4:
                        calcularFolhaPagamento();
                        break;
                    case 5:
                        gerarRelatorio();
                        break;
                    case 0:
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
        System.out.println("1. Register employee");
        System.out.println("2. List employees");
        System.out.println("3. Remove employee");
        System.out.println("4. Calculate payroll");
        System.out.println("5. Generate report");
        System.out.println("0. Exit");
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                double valor = Double.parseDouble(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    // REGISTER EMPLOYEE

    private void cadastrarFuncionario() {
        System.out.println("\n===== REGISTER EMPLOYEE =====");
        System.out.println("1. CLT");
        System.out.println("2. PJ");
        System.out.println("3. Commissioned");

        int tipo = lerInteiro("Choose employee type: ");

        int id = lerInteiro("ID: ");
        String nome = lerTexto("Name: ");
        String cpf = lerTexto("CPF: ");

        Funcionario funcionario;

        switch (tipo) {
            case 1:
                double salarioCLT = lerDouble("Base salary: ");
                funcionario = new FuncionarioCLT(
                        id, nome, cpf, salarioCLT, new HashMap<>()
                );
                break;

            case 2:
                double valorProjeto = lerDouble("Project value: ");
                int quantidadeProjetos = lerInteiro("Number of projects: ");

                funcionario = new FuncionarioPJ(
                        id, nome, cpf, valorProjeto, quantidadeProjetos
                );
                break;

            case 3:
                double salario = lerDouble("Base salary: ");
                double vendas = lerDouble("Sales: ");
                double percentual = lerDouble("Commission percentage: ");

                funcionario = new FuncionarioComissionado(
                        id, nome, cpf, salario, vendas, percentual
                );
                break;

            default:
                System.out.println("Invalid employee type.");
                return;
        }

        funcionarioService.cadastrarFuncionario(funcionario);
        System.out.println("Employee registered successfully.");
    }

    // LIST EMPLOYEES

    private void listarFuncionarios() {
        System.out.println("\n===== EMPLOYEE LIST =====");

        List<Funcionario> funcionarios =
                funcionarioService.listarFuncionarios();

        for (Funcionario funcionario : funcionarios) {
            System.out.println("--------------------------");
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Name: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println(
                    "Remuneration: " + funcionario.calcularRemuneracao()
            );
        }
    }

    // REMOVE EMPLOYEE

    private void removerFuncionario() {
        System.out.println("\n===== REMOVE EMPLOYEE =====");

        int id = lerInteiro("Employee ID: ");

        funcionarioService.removerFuncionario(id);

        System.out.println("Employee removed successfully.");
    }

    // CALCULATE PAYROLL

    private void calcularFolhaPagamento() {
        double total = folhaPagamentoService.calcularFolhaPagamento();

        System.out.println("\nTotal payroll: " + total);
    }

    // GENERATE REPORT

    private void gerarRelatorio() {
        System.out.println(folhaPagamentoService.gerarRelatorio());
    }
}