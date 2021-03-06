package haha.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import haha.entities.Room;
import haha.exceptions.DuplicatedException;
import haha.exceptions.NotFoundException;
import haha.repositories.RoomRepository;

@Component("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Cacheable("rooms")
    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room insert(Room newRoom) {
        List<Room> roomList = roomRepository.findAll();
        String newName = newRoom.getName();
        if (roomList.parallelStream().filter(room -> newName.equals(room.getName())).count() > 0) {
            throw new DuplicatedException("Room name: " + newRoom.getName() + " is registed!");
        }
        newRoom = roomRepository.saveAndFlush(newRoom.setNew());
        return roomRepository.save(newRoom);
    }

    @Override
    @Cacheable("room")
    public Room findById(Long id) {
        return roomRepository.findAll().parallelStream()
                .filter(room -> id.equals(room.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @CachePut("rooms")
    public synchronized Room update(Long id, Room updateRoom) {
        List<Room> roomList = roomRepository.findAll();
        if (roomList.parallelStream().filter(room -> room.getName().equals(updateRoom.getName())).count() > 0) {
            throw new DuplicatedException("Room name: " + updateRoom.getName() + " is registed!");
        }
        return roomList.parallelStream().filter(room -> id.equals(room.getId())).findFirst()
                .map(room -> {
                    room.setName(updateRoom.getName());
                    room.setStatus(updateRoom.getStatus());
                    room.setType(updateRoom.getType());
                    room.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    return roomRepository.update(updateRoom);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @CacheEvict("rooms")
    public synchronized void delete(Long id) {
        roomRepository.findAll().parallelStream().filter(room -> id.equals(room.getId())).findFirst().map(room -> {
            roomRepository.update(room.updateDeleted());
            return true;
        }).orElseThrow(() -> new NotFoundException(id));
    }
}
