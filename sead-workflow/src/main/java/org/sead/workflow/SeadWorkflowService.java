package org.sead.workflow;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.om.OMXMLParserWrapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("service")
public class SeadWorkflowService {

    static {
        // reads the sead-wf.xml to load the workflow configuration
        InputStream inputStream =
                SeadWorkflowService.class.getResourceAsStream("sead-wf.xml");
        OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(inputStream);
        builder.setCache(true);
        OMElement docElement = builder.getDocumentElement();
        System.out.println("Loaded element: " + docElement.getLocalName());
    }

    /**
     * Ping method to check whether the workflow service is up
     *
     * @return ACK
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "SEAD Workflow Service is up!";
    }

    /**
     * Invokes the publish workflow to publish the given Research Object.
     *
     * @param ro - Research Object description
     * @return DOI that is assigned to the published RO
     */
    @POST
    @Path("/publishRO")
    @Consumes("application/json")
    public Response publishRO(@QueryParam("ro") String ro) {
        return Response.ok().build();
    }

}
