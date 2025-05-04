package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Holiday;
import model.HolidayType;
import model.Room;
import model.client.CountryResponse;
import model.client.HolidayResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import repository.HolidayRepository;
import repository.RoomRepository;
import restclient.CountryClient;
import restclient.HolidayClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/room/")
public class RoomResource {

    @Inject
    private RoomRepository roomRepository;

    @Inject
    private HolidayRepository holidayRepository;

    @RestClient
    private CountryClient countryClient;

    @RestClient
    private HolidayClient holidayClient;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAvailableCountries")
    public Response getAvailableCountries() {
        List<CountryResponse> countryResponse = countryClient.getAvailableCountries();
        return Response.ok().entity(countryResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/NextPublicHolidays/{countryCode}")
    public Response getNextPublicHolidays(@PathParam("countryCode") String countryCode) {
        List<HolidayResponse> holidayResponses = holidayClient.getNextPublicHolidays(countryCode);

        for (HolidayResponse hr : holidayResponses) {
            Holiday holiday = convertToHolidayEntity(hr);
            if (!holidayRepository.existsByDateAndCountryCode(holiday.getDate(), holiday.getCountryCode())) {
                holidayRepository.createHoliday(holiday);
            }
        }

        return Response.ok().entity(holidayResponses).build();
    }

    private Holiday convertToHolidayEntity(HolidayResponse hr) {
        Holiday holiday = new Holiday();
        holiday.setDate(hr.getDate());
        holiday.setLocalName(hr.getLocalName());
        holiday.setName(hr.getName());
        holiday.setCountryCode(hr.getCountryCode());
        holiday.setFixed(hr.isFixed());
        holiday.setGlobal(hr.isGlobal());
        holiday.setLaunchYear(hr.getLaunchYear());

        List<HolidayType> types = hr.getTypes().stream()
                .map(type -> {
                    HolidayType holidayType = new HolidayType();
                    holidayType.setType(type);
                    holidayType.setHoliday(holiday);
                    return holidayType;
                })
                .collect(Collectors.toList());

        holiday.setTypes(types);
        return holiday;
    }
}
