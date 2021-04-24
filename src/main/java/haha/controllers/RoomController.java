package haha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import haha.entities.Room;
import haha.services.RoomService;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{hotelId}/rooms")
    public ResponseEntity<List<Room>> getByHotelId(@PathVariable Long hotelId) {
        List<Room> roomList = roomService.findByHotelId(hotelId);
        if (CollectionUtils.isEmpty(roomList)) {
            return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}/room/{id}")
    public ResponseEntity<Room> getByIdAndHotelId(@PathVariable Long id, @PathVariable Long hotelId) {
        Room room = roomService.findByIdAndHotelId(id, hotelId);
        if (room == null) {
            return new ResponseEntity<Room>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Room>(room, HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRoomList() {
        List<Room> roomList = roomService.getAllRoom();
        if (CollectionUtils.isEmpty(roomList)) {
            return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
    }
}
