package artopia.services.commands;

import artopia.commands.AuthorsCommand;
import artopia.commands.infrastructure.AbstractCommand;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vehsamrak
 */
public class CommandRepositoryTest extends Assert
{
    @Test
    public void findByName_commandExistsInRepository_returnsCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand authorsCommand = commandRepository.findByName("authors");

        assertTrue(authorsCommand instanceof AuthorsCommand);
    }

    @Test
    public void findByName_commandDoesNotExistsInRepository_returnsNull()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand notExistingTestCommand = commandRepository.findByName("notExistingTestCommand");

        assertNull(notExistingTestCommand);
    }

    private CommandRepository createCommandRepository()
    {
        return new CommandRepository();
    }
}
