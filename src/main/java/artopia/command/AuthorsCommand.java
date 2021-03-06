package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Vehsamrak
 */
public class AuthorsCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(String[] arguments, User user, ServiceLocator serviceLocator)
    {
        return new CommandResult("authors", "Автор проекта: Petr Karmashev (Vehsamrak)");
    }

    @Override
    public String getDescription()
    {
        return "информация об авторах проекта";
    }
}
