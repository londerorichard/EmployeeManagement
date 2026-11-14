package view;

import service.FuncionarioService;
import service.FolhaPagamentoService;

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
            System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
            System.out.println("1. Register employee");
            System.out.println("2. List employees");
            System.out.println("3. Remove employee");
            System.out.println("4. Calculate payroll");
            System.out.println("5. Generate report");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        // Register employee
                        break;
                    case 2:
                        // List employees
                        break;
                    case 3:
                        // Remove employee
                        break;
                    case 4:
                        System.out.println(
                                "Total payroll: "
                                        + folhaPagamentoService.calcularFolhaPagamento()
                        );
                        break;
                    case 5:
                        System.out.println(
                                folhaPagamentoService.gerarRelatorio()
                        );
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
}