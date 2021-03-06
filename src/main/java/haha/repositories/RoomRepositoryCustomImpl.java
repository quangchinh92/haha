package haha.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import haha.entities.Room;

@Repository
@Transactional
public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Room update(Room room) {
        Query query = entityManager
                .createNativeQuery("UPDATE room SET name = ?, type = ?, status = ?, deleted = ? WHERE id =?",
                        Room.class);
        query.setParameter(1, room.getName());
        query.setParameter(2, room.getType());
        query.setParameter(3, room.getStatus());
        query.setParameter(4, room.getDeleted());
        query.setParameter(5, room.getId());
        query.executeUpdate();
        return room;
    }

}
