/*
 *
 * Copyright 2015 University of Michigan
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
 */

package org.sead.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/repositories")
public abstract class Repositories {


	/**
	 * 
	 * Register a new Repository
	 * 
	 * @param registerRepository
	 *            {initialProfile, &lt;initialProfile&gt;}
	 * 
	 * <br>
	 *            where initialProfile is a json profile document for the new
	 *            repository.
	 * 
	 * @see Example input file: < a href = "./impl/ideals.json">./impl/ideals.json</a>
	 * 
	 * @return 200: {response: "success", id : &lt;ID&gt;} <br>
	 *         400 Bad Request: {response: "failure", reason : &lt;string&gt;} <br>
	 *         409 Conflict: {response: "failure", reason : &lt;string&gt;}
	 * 
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response registerRepository(String profileString);

	/**
	 * Return the list of repositories
	 * 
	 * @return [
	 * 			{"orgidentifier":<id>, "repositoryUrl":<url>},
	 * 			"repositoryName":<name>, "lastUpdate":<profile update date>
	 * 		   ]
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getRepositoryList();

	/**
	 * Return the profile for a given repository
	 * 
	 * @param id
	 *            the assigned repository ID
	 * 
	 * @return : json-ld profile document - as submitted
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getRepositoryProfile(@PathParam("id") String id);

	/**
	 * Update the profile for a given repository. The orgidentifier element in the new profile must exist and must match the {id} being PUT.
	 * 
	 * @param id
	 *            the assigned repository ID
	 * 
	 * @return 200 OK: {response: "success", id : &lt;ID&gt;} <br>
	 *         400 Bad Request: {response: "failure", reason : &lt;string&gt;} <br>
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response setRepositoryProfile(@PathParam("id") String id, String profile);

	/**
	 * Unregister a repository and remove its profile
	 * 
	 * @param id
	 *            the assigned repository ID
	 * 
	 * @return 200 OK: {response: "success", id : &lt;ID&gt;} <br>
	 *         400 Bad Request: {response: "failure", reason : &lt;string&gt;} <br>
	 */
	@DELETE
	@Path("/{id}")
	public abstract Response unregisterRepository(@PathParam("id") String id);

	/**
	 * Return the set of ROs for a given repository
	 * 
	 * @param  - filter by status?
	 * 
	 * @return :array of JSON objects that include the Identifier and Title of the Aggregation, the Repository id, and the list of Status messages 
	 * 
	 */
	@GET
	@Path("/{id}/researchobjects")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROsByRepository(@PathParam("id") String id);
	
	/**
	 * Return the set of new ROs for a given repository (those that have no status messages from the repository
	 * associated with them).
	 * 
	 * @return :array of JSON objects that include the Identifier and Title of the Aggregation, the Repository id, 
	 * and the list of Status messages (e.g. those from sead-cpr itself)
	 * 
	 */
	@GET
	@Path("/{id}/researchobjects/new")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getNewROsByRepository(@PathParam("id") String id);
	
}
