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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/researchobjects")
public abstract class ResearchObjects {

    /**
     * Request publication of a new research object
     *
     * @param publicationRequestString
     *            {
     *            &ensp;"@context": [], </br>
     *            &ensp;Aggregation: &lt;ContentObject&gt;, </br>
     *            &ensp;Preferences: {&lt;Preferences list&gt;}, </br>
     *            &ensp;Aggregation Statistics: {&lt;Aggregation Statistics List&gt;}, </br>
     *            &ensp;Repository: {&lt;RepositoryId&gt;}</br>
     *            }<br>
     *            where Content is a json object including basic metadata and
     *            the unique ID for the entity the user wants to publish. <br>
     *            preferences is a json list of options chosen from those
     *            available. <br>
     *            Repository is the ID of the repository as defined within SEAD.</br>
     *
     * @return 201 Created : {identifier: &lt;research object identifier&gt;} <br>
     *         400 Bad Request: &lt;failure string&gt; <br>
     *
     */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	abstract public Response startROPublicationProcess(String publicationRequestString, @Context HttpServletRequest request);

	/**
	 * Requests matching repositories, Note - request does not create a persistent object in the CP services
	 * 
	 * @param publicationRequest
     *            {
     *            &ensp;"@context": [], </br>
     *            &ensp;Aggregation: &lt;ContentObject&gt;, </br>
     *            &ensp;Preferences: {&lt;Preferences list&gt;}, </br>
     *            &ensp;Aggregation Statistics: {&lt;Aggregation Statistics List&gt;}</br>
     *            }<br>
	 *            where Aggregation is a json object including basic metadata and
	 *            the unique ID for the entity the user wants to publish. <br>
	 *            Preferences is a json list of options chosen from those
	 *            available.</br>
     *            Aggregation Statistics are values for metadata that
	 *            correspond to the rule matching requirements <br>
	 *
	 * 
	 * @return 200:[<br>
     *          &ensp;{<br>
     *          &ensp;&ensp;"orgidentifier": &lt;repository identifier&gt;,<br>
     *          &ensp;&ensp;"repositoryName": &lt;repository name&gt;,<br>
     *          &ensp;&ensp;"Per Rule Scores": [<br>
     *          &ensp;&ensp;&ensp;{<br>
     *          &ensp;&ensp;&ensp;&ensp;"Rule Name": &lt;rule name&gt;,<br>
     *          &ensp;&ensp;&ensp;&ensp;"Score": &lt;per rule score&gt;,<br>
     *          &ensp;&ensp;&ensp;&ensp;"Message": &lt;message&gt;<br>
     *          &ensp;&ensp;&ensp;}<br>
     *          &ensp;&ensp;],<br>
     *          &ensp;&ensp;"Total Score": &lt;total score&gt;<br>
     *          &ensp;}, ...<br>
     *          ]<br>
	 *         400 Bad Request:  {Failure: &lt;string&gt;} <br>
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
	 * @return [ <br>
     *          &ensp;{"Rule name": &lt;name&gt;,<br>
     *          &ensp;&ensp; "Repository Trigger": &lt;the profile metadata needed to trigger this rule&gt;,<br>
     *          &ensp;&ensp; "Publication Trigger": &lt;publication trigger&gt; <br>
     *          &ensp;}<br>
     *         ]
	 */
	@GET
	@Path("/matchingrepositories/rules")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getRulesList();

    /**
     * Return the list of requests
     *
     * @param  purpose
     *             filter by the purpose flag of the research object; the values for the 'purpose' can be 'Production' or 'Testing-Only'
     *
     * @return 200 : [<br>
     * 			&ensp;{<br>
     * 		    &ensp;&ensp;Status: [array of statuses],<br>
     * 		    &ensp;&ensp;Aggregation Statistics: {&lt;Aggregation Statistics List&gt;}, </br>
     * 		    &ensp;&ensp;Repository: {&lt;RepositoryId&gt;}</br>
     * 		    &ensp;}, ...<br>
     * 		   ]<br>
     * 		   400 Bad Request: {Error: &lt;string&gt;} <br>
     */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROsList(@QueryParam("Purpose") final String purpose);

    /**
     * Return the list of new requests (no status from repository)
     *
     * @param  purpose
     *             filter by the purpose flag of the research object; the values for the 'purpose' can be 'Production' or 'Testing-Only'
     *
     * @return 200 : [<br>
     * 			&ensp;{<br>
     * 		    &ensp;&ensp;Status: [array of statuses],<br>
     * 		    &ensp;&ensp;Aggregation Statistics: {&lt;Aggregation Statistics List&gt;}, </br>
     * 		    &ensp;&ensp;Repository: {&lt;RepositoryId&gt;}</br>
     * 		    &ensp;}, ...<br>
     * 		   ]<br>
     * 		   400 Bad Request: {Error: &lt;string&gt;} <br>
     */
	@GET
	@Path("/new/")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getNewROsList(@QueryParam("Purpose") final String purpose);

    /**
     * Return the profile and status for a given publication
     *
     * @param id
     *            the assigned ro/publication ID
     *
     * @return 200 OK: &lt;research object profile&gt; <br>
     *         404 Not Found:  {Error: &lt;string&gt;} <br>
     *         301 Moved Permanently:  {Error: &lt;string&gt;} <br>
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
     * @return 200 OK: &lt;ORE Map&gt; <br>
     *         404 Not Found:  {Error: &lt;string&gt;} <br>
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
     * @return 200 OK: &lt;FGDC metadata document&gt; <br>
     *         404 Not Found:  {Error: &lt;string&gt;} <br>
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
     * @return 200 OK: &lt;research object status array&gt; <br>
     *         404 Not Found:  {Error: &lt;string&gt;} <br>
     */
	@GET
	@Path("/{id}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getROStatus(@PathParam("id") String id);

	/**
	 * Update the status for a given publication/ro<br>
     * If stage=="Success", a FGDC metadata document is created for DataONE records and DOI is sent to project space/data source<br>
     * If this RO has an alternate RO, delete the old RO request/OREMap and add oldRO ID to the new RO request<br>
	 * 
	 * @param id
	 *            the assigned ro/publication ID
     * @param status
     *            Body : { "reporter": &lt;reporter&gt;, "stage": &lt;stage&gt;, "message": &lt;message&gt; }<br>
     * Reporter: the entity sending status, e.g. repository (use the orgidentifier term used as an id in repository profiles)<br>
     * Stage: short string describing stage: Recommended values are "Receipt Acknowledged", "Pending", "Success", "Failure"<br>
     * Message" longer string describing the status. For "Success", the message MUST be the persistent identifier assigned to the research object<br>
     * A timestamp will be appended by the services.
     *
     *
     * @return 200 OK: Upon successful update of status <br>
     *         404 Not Found:  {Error: &lt;string&gt;} <br>
     *         400 Bad Request:  {Error: &lt;string&gt;} <br>
	 */
	@POST
	@Path("/{id}/status")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response setROStatus(@Context HttpServletRequest request, @PathParam("id") String id,
			String status);

    /**
     * Rescind a publication request
     *
     * @param id
     *            the assigned publication/ro ID
     *
     * @return 200 OK: &lt;"RO Successfully Deleted"&gt; <br>
     *         404 Not Found:  &lt;error message&gt; <br>
     *         400 Bad Request: &lt;error message&gt; <br>
     */
	@DELETE
	@Path("/{id}")
	public abstract Response rescindROPublicationRequest(
			@PathParam("id") String id);

}
