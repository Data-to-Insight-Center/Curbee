/*
 *
 * Copyright 2015 University of Michigan, 2015 The Trustees of Indiana University,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 *
 * @author myersjd@umich.edu
 * @author charmadu@umail.iu.edu
 */

package org.sead.api.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.sead.api.Search;
import org.sead.api.util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * See abstract base class for documentation of the rest api. Note - path
 * annotations must match base class for documentation to be correct.
 */

@Path("/search")
public class SearchImpl extends Search {
    private WebResource resource;
    private String serviceUrl;
    private CacheControl control = new CacheControl();

    private WebResource resource(){
        return resource;
    }

	public SearchImpl() {
        this.serviceUrl = Constants.pdtUrl;
        resource = Client.create().resource(serviceUrl);
        control.setNoCache(true);
	}

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPublishedROs(@QueryParam("repo") String repoName) {
        WebResource webResource = resource().path("search");

        if(repoName != null) {
            webResource = webResource.queryParam("repo", repoName);
        }

        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .get(ClientResponse.class);

        return Response.status(response.getStatus()).entity(response
                .getEntity(new GenericType<String>() {})).cacheControl(control).build();

    }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFilteredListOfROs(String filterString, @QueryParam("repo") String repoName) {
        WebResource webResource = resource().path("search");

        if(repoName != null) {
            webResource = webResource.queryParam("repo", repoName);
        }

        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .post(ClientResponse.class, filterString);

        return Response.status(response.getStatus()).entity(response
                .getEntity(new GenericType<String>() {})).cacheControl(control).build();
	}



}
