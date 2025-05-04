package repository;

import exception.MemberException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Member;
import model.MemberReservation;
import model.Reservation;

import java.util.List;

@Dependent
public class MemberRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Member create(Member m) {
        return em.merge(m);
    }

    @Transactional
    public List<Member> getMembers() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Transactional
    public MemberReservation createMemberReservation(MemberReservation memberReservation) throws MemberException {
        Member newMember = em.find(Member.class, memberReservation.getMember().getId());
        Reservation newReservation = em.find(Reservation.class, memberReservation.getReservation().getId());

        if (newMember == null || newReservation == null) {
            throw new MemberException("Member or reservation does not exist");
        }

        MemberReservation mr = new MemberReservation(newMember, newReservation);

        return em.merge(mr);
    }


}
