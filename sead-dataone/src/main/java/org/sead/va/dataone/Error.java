/*
 * Copyright 2013 The Trustees of Indiana University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.sead.va.dataone;

import org.sead.va.dataone.util.Constants;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * In case of error send to user
 */

@Path("/mn/v1/error")
public class Error {

    @POST
    public Response getError(String message) {
        System.out.println("DataONE MN API - Error : " + message);
        Email emailSender = new Email("gmail", Constants.emailUsername, Constants.emailPassword);
        emailSender.sendEmail(Constants.emailUsername, "DataONE MN API - Error Message", message);
        return Response
                .status(Response.Status.OK)
                .entity("true")
                .type(MediaType.TEXT_XML)
                .build();
    }
}
