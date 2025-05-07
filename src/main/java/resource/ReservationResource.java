package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Reservation;
import repository.ReservationRepository;

import java.util.List;

@Path("/reservation/")
public class ReservationResource {

    @Inject
    private ReservationRepository reservationRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllReservations")
    public Response getAllReservations() {
        List<Reservation> re = reservationRepository.findAll();

        return Response.ok().entity(re).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createReservation")
    public Response createReservation(Reservation reservation) {
        Reservation r = reservationRepository.create(reservation);

        return Response.ok().entity(r).build();
    }
}
