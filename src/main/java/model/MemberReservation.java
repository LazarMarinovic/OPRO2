package model;

import jakarta.persistence.*;

@Entity
public class MemberReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberReservation_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    Reservation reservation;

    public MemberReservation() {
        super();
    }

    public MemberReservation(Member member, Reservation reservation) {
        super();
        this.member = member;
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((member == null) ? 0 : member.hashCode());
        result = prime * result + ((reservation == null) ? 0 : reservation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MemberReservation other = (MemberReservation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }else if (!id.equals(other.id))
            return false;
        if (member == null) {
            if (other.member != null)
                return false;
        } else if (!member.equals(other.member))
            return false;
        if (reservation == null) {
            if (other.reservation != null)
                return false;
        }else if (reservation.equals(other.reservation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemberReservation{" +
                "id=" + id +
                ", member=" + member +
                ", reservation=" + reservation +
                '}';
    }
}
