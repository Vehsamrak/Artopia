package artopia.service.room;

import artopia.entitiy.room.Room;
import artopia.service.locator.AbstractService;
import artopia.service.locator.Service;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Vehsamrak
 */
public class RoomRepository extends AbstractService
{
    private ArrayList<Room> roomList = new ArrayList<>();

    /**
     * Список всех комнат
     */
    public RoomRepository(RoomParser roomParser)
    {
        this.roomList = roomParser.parseRooms(new File("src/main/resources/zones/medieval-town.xml"));
    }

    /**
     * @param id Идентификатор комнаты
     * @return Комната найденная по идентификатору, или null если комната не найдена
     */
    public Room findById(String id)
    {
        id = id.toLowerCase();

        for (Room room : this.roomList) {
            if (room.getId().equals(id)) {
                return room;
            }
        }

        return null;
    }

    @Override
    public Service getName()
    {
        return Service.ROOM_REPOSITORY;
    }
}
