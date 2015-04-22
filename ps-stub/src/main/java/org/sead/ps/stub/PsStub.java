package org.sead.ps.stub;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;

public class PsStub {

    public static void main(String[] args) {
        PsStub stub = new PsStub();
        stub.pingVAWorkflow();
    }

    public void pingVAWorkflow() {
        try {
            WebResource webResource =  Client.create().resource("http://localhost:8080/sead-wf");
            ClientResponse response = webResource.path("service")
                    .path("ping")
                    .get(ClientResponse.class);
            System.out.println("Response Status: " + response.getStatus());
            StringWriter writer = new StringWriter();
            IOUtils.copy(response.getEntityInputStream(), writer);
            System.out.println("Response: " + writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
