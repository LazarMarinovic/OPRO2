package resource;

import exception.MemberException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Member;
import model.MemberReservation;
import repository.MemberRepository;

@Path("/member/")
public class MemberResource {

    @Inject
    private MemberRepository memberRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMemberReservation")
    public Response createMemberReservation(MemberReservation memberReservation) {
        MemberReservation mb = null;
        try {
            mb = memberRepository.createMemberReservation(memberReservation);
        } catch (MemberException e) {
            return Response.ok().entity(e.getMessage()).build();
        }
        return Response.ok().entity(mb).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createMember")
    public Response createMember(Member member) {
        Member m = memberRepository.create(member);

        return Response.ok().entity(m).build();
    }
}
