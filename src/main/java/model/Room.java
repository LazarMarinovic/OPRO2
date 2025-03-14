package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipmentList = new ArrayList<>();

    // Getters, Setters, Konstruktori
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
}