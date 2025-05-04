package repository;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Reservation;

import java.util.List;

@Dependent
public class ReservationRepository {
    @Inject
    EntityManager em;

    @Transactional
    public Reservation create(Reservation r) {
        return em.merge(r);
    }

    @Transactional
    public List<Reservation> findAll() {

        return em.createNamedQuery(Reservation.GET_ALL_RESERVATIONS, Reservation.class).getResultList();
    }

}
