package haha.payload;

import javax.validation.constraints.NotEmpty;

import haha.entities.Room;

public class RoomInsert {

    @NotEmpty(message = "Name is not empty!")
    private String name;

    @NotEmpty(message = "Type is not empty!")
    private String type;

    @NotEmpty(message = "HotelId is not empty!")
    private String hotelId;

    public Room createEntity() {
        Room room = new Room();
        room.setName(name);
        room.setType(Integer.valueOf(type));
        room.setStatus(0);
        return room;
    }
}
