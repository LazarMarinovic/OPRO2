package repository;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Equipment;
import model.Member;
import model.Room;

import java.util.HashSet;
import java.util.List;

@Dependent
public class RoomRepository {

    @Inject
    private EntityManager em;

    @Transactional
    public Room createRoom(Room room) {
        return em.merge(room);
    }

    @Transactional
    public List<Room> getAllRooms() {
        List<Room> rooms = em.createNamedQuery(Room.GET_ALL_ROOMS, Room.class).getResultList();

        for (Room room : rooms) {
            List<Equipment> equipment = em.createNamedQuery(Equipment.GET_EQUIPMENT_FOR_ROOM, Equipment.class)
                    .setParameter("id", room.getId()).getResultList();

            room.setEquipmentList(equipment);
        }

        return rooms;
    }
}
