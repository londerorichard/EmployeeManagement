import repository.FuncionarioRepository;
import service.FuncionarioService;
import service.FolhaPagamentoService;
import view.FuncionarioView;

public class Main {

    public static void main(String[] args) {

        // Initialize repository
        FuncionarioRepository repository = new FuncionarioRepository();

        // Initialize services
        FuncionarioService funcionarioService =
                new FuncionarioService(repository);

        FolhaPagamentoService folhaPagamentoService =
                new FolhaPagamentoService(repository);

        // Initialize View
        FuncionarioView view = new FuncionarioView(
                funcionarioService,
                folhaPagamentoService
        );

        // Start application
        view.iniciar();
    }
}