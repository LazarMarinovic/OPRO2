package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Room.GET_ALL_ROOMS , query = "Select r from Room r")
public class Room {
    public static final String GET_ALL_ROOMS = "Room.getAllRooms";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    private Long id;
    private String name;
    private int capacity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipment> equipmentList = new ArrayList<>();

    public Room() {
    }

    public Room(Long id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
        equipment.setRoom(this);
    }

    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
        equipment.setRoom(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return capacity == room.capacity && Objects.equals(id, room.id) && Objects.equals(name, room.name) && Objects.equals(equipmentList, room.equipmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, equipmentList);
    }
}