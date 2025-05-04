package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = Member.GET_ALL_MEMBERS, query = "Select m from Member m")

public class Member {

    public static final String GET_ALL_MEMBERS = "Reservation.GetAllMembers";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

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

    @ManyToMany
    @JoinTable(
            name = "member_reservation",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservations = new ArrayList<>();

}