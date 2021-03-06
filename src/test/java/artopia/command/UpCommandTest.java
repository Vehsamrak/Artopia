package artopia.command;

import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import artopia.exception.ServiceNotFound;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import artopia.service.room.RoomRepository;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
public class UpCommandTest extends Assert
{

    @Test
    public void execute_userInRoom_userMovedToAnotherRoom() throws Exception
    {
        User user = this.createUser();
        String currentRoomId = user.getRoomId();

        UpCommand moveCommand = new UpCommand();
        String[] arguments = {};
        moveCommand.execute(arguments, user, this.createServiceLocator());

        assertNotEquals(currentRoomId, user.getRoomId());
        assertEquals("test-up", user.getRoomId());
    }

    private ServiceLocator createServiceLocator() throws ServiceNotFound
    {
        RoomRepository roomRepository = createRoomRepository();

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.ROOM_REPOSITORY)).thenReturn(roomRepository);

        return serviceLocator;
    }

    private RoomRepository createRoomRepository()
    {
        Room centerRoom = new Room(
                "test-center",
                "Center",
                "Center test room",
                new Exit("test-north", null, false),
                new Exit("test-east", null, false),
                new Exit("test-south", null, false),
                new Exit("test-west", null, false),
                new Exit("test-up", null, false),
                new Exit("test-down", null, false)
        );

        Room westRoom = new Room(
                "test-up",
                "Up",
                "Upper test room",
                null,
                null,
                null,
                null,
                null,
                new Exit("test-center", null, false)
        );

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.findById("test-center")).thenReturn(centerRoom);
        when(roomRepository.findById("test-up")).thenReturn(westRoom);

        return roomRepository;
    }

    private User createUser() throws EmptyPassword, EmptyUsername
    {
        User user = new User("Tester", "password");
        user.setRoomId("test-center");

        return user;
    }
}
