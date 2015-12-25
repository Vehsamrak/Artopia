package artopia.services.commands.errors;

/**
 * @author Rottenwood
 */
public class CommandNotFound extends AbstractCommandError {

    @Override
    public String getErrorMessage() {
        return "Команда не найдена. Введи help для просмотра списка команд.";
    }
}
