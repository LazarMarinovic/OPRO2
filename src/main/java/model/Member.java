package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "member_equipment",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> reservedEquipment = new ArrayList<>();

    // Getters, Setters, Konstruktori
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Equipment> getReservedEquipment() {
        return reservedEquipment;
    }

    public void setReservedEquipment(List<Equipment> reservedEquipment) {
        this.reservedEquipment = reservedEquipment;
    }

    public void addEquipment(Equipment equipment) {
        reservedEquipment.add(equipment);
        equipment.getMembers().add(this);
    }

    public void removeEquipment(Equipment equipment) {
        reservedEquipment.remove(equipment);
        equipment.getMembers().remove(this);
    }
}