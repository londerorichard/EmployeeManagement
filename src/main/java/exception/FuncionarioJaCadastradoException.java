package exception;

public class FuncionarioJaCadastradoException extends RuntimeException {

    public FuncionarioJaCadastradoException(String message) {
        super(message);
    }
}