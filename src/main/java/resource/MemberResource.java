package resource;

import exception.MemberException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Member;
import model.MemberReservation;
import model.Room;
import repository.MemberRepository;

import java.util.List;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllMembers")
    public Response getAllMembers() {
        List<Member> m = memberRepository.getMembers();

        return Response.ok().entity(m).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createMember")
    public Response createMember(Member member) {
        Member m = memberRepository.create(member);

        return Response.ok().entity(m).build();
    }
}
