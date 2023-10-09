package todo.exceptions;

public class InvalidPasswordException extends RuntimeException {

        public InvalidPasswordException(String message) {
            super("Senha inválida");
        }
}
