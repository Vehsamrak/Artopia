package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Vehsamrak
 */
public class LookCommandTest extends Assert
{

    @Test
    public void execute_noParameters_returnsResponseContainingDefaultLookMessage()
    {
        CommandResult commandResult = new LookCommand().execute(mock(User.class));

        assertFalse(commandResult.haveErrors());
        assertEquals("Ты осмотрелся.", commandResult.toString());
    }
}