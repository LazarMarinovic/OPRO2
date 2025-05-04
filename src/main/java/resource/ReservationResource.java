package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Reservation;
import model.Room;
import repository.ReservationRepository;

@Path("/reservation/")
public class ReservationResource {

    @Inject
    private ReservationRepository reservationRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createReservation")
    public Response createReservation(Reservation reservation) {
        Reservation r = reservationRepository.create(reservation);

        return Response.ok().entity(r).build();
    }
}
