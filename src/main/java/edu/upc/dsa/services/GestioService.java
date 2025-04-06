package edu.upc.dsa.services;

import edu.upc.dsa.GestioAeroport;
import edu.upc.dsa.GestioAeroportImpl;
import edu.upc.dsa.models.Avio;
import edu.upc.dsa.models.Vol;
import edu.upc.dsa.models.Maleta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Queue;

@Api(value = "/avion", description = "Endpoint to Maleta Service")
@Path("/avion")
public class GestioService {

    private GestioAeroport pm;

    public GestioService() {
        this.pm = GestioAeroportImpl.getInstance();

        pm.afegirAvio("01", "Boeing777", "Quatar Airways");
        pm.afegirAvio("02", "AirbusA380", "Vueling");
        pm.afegirVol("V1", "01", "Barcelona", "Paris", "8:00", "10:00");
        pm.afegirVol("V2", "02", "Reus", "Girona", "10:00", "10:45");
        pm.afegirMaleta("Anton", "V1");
        pm.afegirMaleta("Armenio", "V2");
        pm.afegirMaleta("Gisbert", "V1");
    }

    @GET
    @ApiOperation(value = "Obtenir maletes de vol especific", notes = "get")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Maleta.class, responseContainer = "Queue")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maletesVol(@PathParam("id") String id) {
        if(id == null || id.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Queue<Maleta> maletas = this.pm.obtenirMaletesVol(id);
        if(maletas == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        GenericEntity<Queue<Maleta>> entity = new GenericEntity<Queue<Maleta>>(maletas) {};
        return Response.ok(entity).build();
    }

    @POST
    @ApiOperation(value = "crear nova Maleta", notes = "post")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Maleta.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/maleta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newMaleta(@QueryParam("usuario") String usuario, @QueryParam("idvuelo") String idvuelo) {
        if (usuario == null || idvuelo == null)
            return Response.status(500).build();
        Maleta m = this.pm.afegirMaleta(usuario, idvuelo);
        return Response.status(201).entity(m).build();
    }

    @POST
    @ApiOperation(value = "crear nou vol", notes = "post")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Avio.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/avion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newVol(Vol v) {
        if (v.getId() == null)
            return Response.status(500).entity(v).build();
        this.pm.afegirVol(v.getId(),v.getAvio().getId(),v.getOrigen(),v.getDesti(),v.getHoraSortida(),v.getHoraArribada());
        return Response.status(201).entity(v).build();
    }

    @POST
    @ApiOperation(value = "afegir avio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Avio.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newAvion(Avio a) {
        if (a.getId() == null) {
            return Response.status(500).entity(a).build();
        }
        this.pm.afegirAvio(a.getId(), a.getModel(),a.getCompanyiaAeria());
        return Response.status(201).entity(a).build();
    }
}