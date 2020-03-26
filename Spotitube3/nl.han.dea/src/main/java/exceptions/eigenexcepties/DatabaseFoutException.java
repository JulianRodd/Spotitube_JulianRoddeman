package exceptions.eigenexcepties;

public class DatabaseFoutException extends RuntimeException {
    public DatabaseFoutException(String message) {
        super(message);
    }
}
