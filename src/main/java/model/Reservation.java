package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = Reservation.GET_ALL_RESERVATIONS, query = "Select r from Reservation r")

public class Reservation {

    public static final String GET_ALL_RESERVATIONS = "Reservation.GetAllReservations";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    private Long id;
    private LocalDateTime reservationDate;

    // Getters, Setters, Konstruktori
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

}