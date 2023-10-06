package todo.exception;

public class InvalidPasswordException extends RuntimeException {

        public InvalidPasswordException(String message) {
            super("Senha inválida");
        }
}
