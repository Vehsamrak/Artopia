package artopia.handler;

/**
 * @author Rottenwood
 */
public final class ExceptionHandler {
    public static boolean handle(Exception exception) {
        exception.printStackTrace();

        return true;
    }
}
