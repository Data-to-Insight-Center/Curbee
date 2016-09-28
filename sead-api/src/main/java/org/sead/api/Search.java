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
 * @author charmadu@umail.iu.edu
 */

package org.sead.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
public abstract class Search {

    /**
     * Return the list of research objects
     *
     * @param repoName
     *            Optional query parameter to specify the repository
     * @return [</br>
     * 			&ensp;{</br>
     * 			 &ensp;&ensp;"Publication Date": &lt;publication date of the research object&gt;,</br>
     * 			 &ensp;&ensp;"Repository": &lt;repository&gt;,</br>
     * 			 &ensp;&ensp;"Creator": &lt;creators(s)&gt;,</br>
     * 			 &ensp;&ensp;"Title": &lt;title&gt;,</br>
     * 			 &ensp;&ensp;"Abstract": &lt;abstract&gt;,</br>
     * 			 &ensp;&ensp;"Publishing Project": &lt;publishing project&gt;,</br>
     * 			 &ensp;&ensp;"Publishing Project Name": &lt;publishing project name&gt;</br>
     * 			&ensp;}</br>
     * 		   ]
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public abstract Response getAllPublishedROs(@QueryParam("repo") String repoName);

    /**
     *
     * Return the list of research objects filtered by filterString
     *
     * @param repoName
     *            Optional query parameter to specify the repository
     *
     * @param filterString
     *          {</br>
     *             &ensp;Creator: &lt;creator search string&gt;,</br>
     *              &ensp;End Date: &lt;end date in MM/dd/yyyy format&gt;,</br>
     *              &ensp;Start Date: &lt;start date in MM/dd/yyyy format&gt;,</br>
     *              &ensp;Title: &lt;title search string&gt;,</br>
     *              &ensp;Search String: &lt;search string to be searched across all the fields&gt;</br>
     *          }
     *
     * @return [</br>
     * 			&ensp;{</br>
     * 			 &ensp;&ensp;"Publication Date": &lt;publication date of the research object&gt;,</br>
     * 			 &ensp;&ensp;"Repository": &lt;repository&gt;,</br>
     * 			 &ensp;&ensp;"Creator": &lt;creators(s)&gt;,</br>
     * 			 &ensp;&ensp;"Title": &lt;title&gt;,</br>
     * 			 &ensp;&ensp;"Abstract": &lt;abstract&gt;,</br>
     * 			 &ensp;&ensp;"Publishing Project": &lt;publishing project&gt;,</br>
     * 			 &ensp;&ensp;"Publishing Project Name": &lt;publishing project name&gt;</br>
     * 			&ensp;}</br>
     * 		   ]
     *
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public abstract Response getFilteredListOfROs(String filterString, @QueryParam("repo") String repoName);



}
