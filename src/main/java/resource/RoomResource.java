package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Room;
import repository.RoomRepository;

import java.util.List;

@Path("/room/")
public class RoomResource {

    @Inject
    private RoomRepository roomRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createRoom")
    public Response addRoom(Room room) {
        Room r = roomRepository.createRoom(room);
        return Response.ok().entity(r).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllRooms")
    public Response getAllRooms() {
        List<Room> rooms = roomRepository.getAllRooms();

        return Response.ok().entity(rooms).build();
    }
}
