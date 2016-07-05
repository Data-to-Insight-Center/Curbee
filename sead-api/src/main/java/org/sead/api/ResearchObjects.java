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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/researchobjects")
public abstract class ResearchObjects {

	/**
	 * Request publication of a new research object
	 * 
	 * @param publicationRequest
	 *            {Aggregation, &lt;ContentObject&gt;, Preferences, {&lt;Preferences
	 *            list&gt;}, Aggregation Statistics {&lt;Aggregation Statistics List
	 *            &gt; Repository, &lt;RepositoryId&gt;}}
	 * 
	 * <br>
	 *            where Content is a json object including basic metadata and
	 *            the unique ID for the entity the user wants to publish. <br>
	 *            preferences is a json list of options chosen from those
	 *            available (see api ____) <br>
	 *            Respository is the ID of the repository as defined within SEAD
	 *            (see api _______) Project Space is the base URL for the source
	 *            Project Space
	 * 
	 * @see Example input file: _______ <br>
	 *      Example output file: _______
	 * 
	 * @return 200: {response: "success", id : &lt;ID&gt;} <br>
	 *         400: {response: "failure", reason : &lt;string&gt;} <br>
	 *         401: {response: "failure", reason : &lt;string&gt;} 409 Conflict:
	 *         {response: "failure", reason : &lt;string&gt;}
	 * 
	 * 
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	abstract public Response startROPublicationProcess(String matchRequest, @Context HttpServletRequest request);

	/**
	 * Requests matching repositories, Note - request does not create a
	 * persistent object in the CP services
	 * 
	 * @param publicationRequest
	 *            {Aggregation, &lt;Aggregation&gt;, Preferences, {&lt;Preferences
	 *            list&gt;}, Aggregation Statistics {&lt;Aggregation Statistics List
	 *            &gt;} }}
	 * 
	 * <br>
	 *            where Aggregation is a json object including basic metadata and
	 *            the unique ID for the entity the user wants to publish. <br>
	 *            preferences is a json list of options chosen from those
	 *            available (see api ____), and Aggregation Statistics are values for metadata that 
	 *            correspond to the rule matching requirements <br>
	 * 
	 * @see Example input file: _______ <br>
	 *      Example output file: _______
	 * 
	 * @return 200: {response: "success", matches : &lt;json ranked list of
	 *         repositories with explanatory notes&gt;} <br>
	 *         400: {response: "failure", reason : &lt;string&gt;} <br>
	 *         401: {response: "failure", reason : &lt;string&gt;} 409 Conflict:
	 *         {response: "failure", reason : &lt;string&gt;}
	 * 
	 * 
	 */
	@POST
	@Path("/matchingrepositories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	abstract public Response makeMatches(String publicationRequest);

	/**
	 * Return the list of rules and their inputs
	 * 
	 * 
	 * @return [ {"Rule name":<name>, "Repository Trigger": <the profile
	 *         metadata needed to trigger this rule>}, ... ]
	 */
	@GET
	@Path("/matchingrepositories/rules")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getRulesList();

	/**
	 * Return the list of requests
	 * 
	 * @param - optional param to filter by status?
	 * 
	 * @return Aggegation (Title, Identifier), Repository, and Status array
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROsList();

	/**
	 * Return the list of new requests (no status from repository)
	 * 
	 * @param - optional param to filter by status?
	 * 
	 * @return Aggegation (Title, Identifier), Repository, and Status array
	 */
	@GET
	@Path("/new/")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getNewROsList();

	/**
	 * Return the profile and status for a given publication
	 * 
	 * @param id
	 *            the assigned ro/publication ID
	 * 
	 * @return : json-ld profile document
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROProfile(@Context HttpServletRequest request, @PathParam("id") String id);
	
	/**
	 * Return the OREMap associated with the give request
	 * 
	 * @param id
	 *            the assigned ro/publication ID
	 * 
	 * @return : json-ld profile document
	 */
	@GET
	@Path("/{id}/oremap")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROOREMap(@PathParam("id") String id);

    /**
     * Return the FGDC metadata associated with the give request
     *
     * @param id
     *            the assigned ro/publication ID
     *
     * @return : FGDC metadata document
     */
    @GET
    @Path("/{id}/fgdc")
    @Produces(MediaType.APPLICATION_XML)
    public abstract Response getFgdc(@PathParam("id") String id);

	/**
	 * Return the status for a given publication
	 * 
	 * @param id
	 *            the assigned ro/publication ID
	 * 
	 * @return : json-ld profile document
	 */
	@GET
	@Path("/{id}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROStatus(@PathParam("id") String id);

	/**
	 * Update the status for a given publication / ro
	 * Reporter: the entity sending status, e.g. repository (use the orgidentifier term used as an id in repository profiles)
	 * Stage: short string describing stage: Recommended values are "Receipt Acknowledged", "Pending", "Success", "Failure"
	 * Message" longer string describing the status. For "Success", the message MUST be the persistent identifier assigned to the research object
	 * 
	 * A timestamp will be appended by the services.
	 * 
	 * Body : { "reporter": <reporter>, "Stage": <stage>, "message":<message> }
	 * 
	 * 
	 * @param id
	 *            the assigned repository ID
	 * 
	 * @return 200 OK: {response: "success", id : &lt;ID&gt;} <br>
	 *         400 Bad Request: {response: "failure", reason : &lt;string&gt;} <br>
	 */
	@POST
	@Path("/{id}/status")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response setROStatus(@Context HttpServletRequest request, @PathParam("id") String id,
			String status);

	/**
	 * Rescind a publication request and mark it as obsolete
	 * 
	 * @param id
	 *            the assigned publication/ro ID
	 * 
	 * @return 200 OK: {response: "success", id : &lt;ID&gt;} <br>
	 *         400 Bad Request: {response: "failure", reason : &lt;string&gt;} <br>
	 */
	@DELETE
	@Path("/{id}")
	public abstract Response rescindROPublicationRequest(
			@PathParam("id") String id);

}
