package main.java.gustavo.mapa.exceptions;

public class ConnectionFactoryException extends Exception {
    public ConnectionFactoryException() {
        super();
    }

    public ConnectionFactoryException(String message) {
        super(message);
    }

    public ConnectionFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionFactoryException(Throwable cause) {
        super(cause);
    }
}
