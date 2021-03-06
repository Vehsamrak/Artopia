package artopia.service.room;

import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Vehsamrak
 */
public class RoomParserTest extends Assert
{

    @Test
    public void getRooms_givenZoneFileWithThreeRooms_roomlistContainsThreeRoomsWithValidParameters() throws Exception
    {
        RoomParser roomParser = new RoomParser();
        File zoneFile = new File("src/test/resources/zones/test.xml");

        ArrayList<Room> roomList = roomParser.parseRooms(zoneFile);
        Room room = roomList.get(2);
        Exit eastExit = room.getEast();
        Exit westExit = room.getWest();

        assertEquals(3, roomList.size());
        assertEquals("square", eastExit.getRoomId());
        assertEquals("door", eastExit.getDoorMessage());
        assertEquals("closed door", westExit.getDoorMessage());
        assertTrue(eastExit.isClosed());
        assertFalse(westExit.isClosed());
    }
}
