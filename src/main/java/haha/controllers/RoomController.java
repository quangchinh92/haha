package haha.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import haha.entities.Room;
import haha.payload.RoomInsert;
import haha.services.RoomService;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{hotelId}/rooms")
    public List<Room> getByHotelId(@PathVariable Long hotelId) {
        return roomService.findByHotelId(hotelId);
    }

    @GetMapping("/{hotelId}/room/{id}")
    public Room getByIdAndHotelId(@PathVariable Long id, @PathVariable Long hotelId) {
        return roomService.findByIdAndHotelId(id, hotelId);
    }

    @GetMapping("/rooms")
    public List<Room> getRoomList() {
        return roomService.getAllRoom();
    }

    @PostMapping("/room/add")
    public Room inserRoom(@Valid @RequestBody RoomInsert roomInsert) {
        return roomService.insert(roomInsert.createEntity());
    }

    @PutMapping("/room/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.update(id, room);
    }

    @GetMapping("/room/{id}")
    public Room getById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @DeleteMapping("/room/{id}")
    public void deleteById(@PathVariable Long id) {
        roomService.delete(id);
    }
}
