package org.seadva.metadatagen.service;

import org.seadva.metadatagen.OREMetadataGen;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Root resource (exposed at "rest" path)
 */
@Path("rest")
public class MetadataGenerator {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/getMetadata")
    @Produces(MediaType.APPLICATION_XML)
    public Response getIt(@QueryParam("entityId") String entityId,
                        @QueryParam("type") String metadataType) throws URISyntaxException {

        OREMetadataGen oreMetadataGen = new OREMetadataGen();
        String response = "";
        response = oreMetadataGen.generateMetadata(entityId);

        return Response.ok(response
        ).build();
    }
}
