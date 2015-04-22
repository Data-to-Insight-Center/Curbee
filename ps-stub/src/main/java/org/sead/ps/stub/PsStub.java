package org.sead.ps.stub;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PsStub {

    private static final String SEAD_WF_URL = "http://localhost:8080/sead-wf";

    public static void main(String[] args) {
        PsStub stub = new PsStub();
        stub.pingVAWorkflow();
        stub.postJSONRO();
    }

    public void pingVAWorkflow() {
        WebResource webResource = Client.create().resource(SEAD_WF_URL);

        ClientResponse response = webResource.path("service")
                .path("ping")
                .get(ClientResponse.class);

        System.out.println("Response Status: " + response.getStatus());
        System.out.println("Response: " + response.getEntity(String.class));
    }

    public void postJSONRO() {
        WebResource webResource = Client.create().resource(SEAD_WF_URL);

        String input = "{\"tag:cet.ncsa.uiuc.edu,2008:/bean/Collection/43DEC828-FDB7-4F41-AD1E-DD21A44752E6\":" +
                "{\"Identifier\":\"tag:cet.ncsa.uiuc.edu,2008:/bean/Collection/43DEC828-FDB7-4F41-AD1E-DD21A44752E6\"," +
                "\"Title\":\"yourtest\",\"Date\":\"2015-02-20T14:34:31.037Z\",\"Uploaded By\":" +
                "\"http://cet.ncsa.uiuc.edu/2007/person/myersjd@umich.edu\",\"Abstract\":\"Anthropomorphism! It&#39;s " +
                "everywhere. walter smith.\"},\"tag:medici@uiuc.edu,2009:col_GNnVdztCO3zq6bpSWOl8GQ\":{\"Identifier\":" +
                "\"tag:medici@uiuc.edu,2009:col_GNnVdztCO3zq6bpSWOl8GQ\",\"Title\":\"testing\",\"Date\":" +
                "\"2015-03-05T21:48:32.355Z\",\"Uploaded By\":\"http://cet.ncsa.uiuc.edu/2007/person/myersjd@umich.edu\"," +
                "\"Abstract\":\"hydrology at it&#39;s best\",\"Creator\":\"Myers, James : http://sead-vivo.d2i.indiana.edu:8080" +
                "/sead-vivo/individual/n6502\"},\"@context\":{\"Identifier\":\"http://purl.org/dc/elements/1.1/identifier\"," +
                "\"Title\":\"http://purl.org/dc/elements/1.1/title\",\"Date\":\"http://purl.org/dc/terms/created\"," +
                "\"Uploaded By\":\"http://purl.org/dc/elements/1.1/creator\",\"Abstract\":\"http://purl.org/dc/terms/abstract\"," +
                "\"Contact\":\"http://sead-data.net/terms/contact\",\"Creator\":\"http://purl.org/dc/terms/creator\"}}";

        // POST RO
        ClientResponse response = webResource.path("service")
                .path("publishRO")
                .accept("application/json")
                .type("application/json")
                .post(ClientResponse.class, input);

        System.out.println("Response Status: " + response.getStatus());
        System.out.println("Response: " + response.getEntity(String.class));
    }

}
