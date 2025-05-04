package model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = Equipment.GET_EQUIPMENT_FOR_ROOM, query = "Select e from Equipment e where e.room.id = :id")
public class Equipment {

    public static final String GET_EQUIPMENT_FOR_ROOM = "Equipment.getEquipmentForRoom";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eq_seq")
    private Long id;
    private String name;
    private String type;

    @ManyToOne
    private Room room;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}