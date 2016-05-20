/*
 *
 * Copyright 2015 The Trustees of Indiana University
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
 * @author charmadu@umail.iu.edu
 */

package org.sead.va.dataone.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class Constants {

    public static String mongoHost;
    public static int mongoPort;

    public static String NODE_IDENTIFIER;
    public static String SUBJECT;
    public static String BASE_URL;

    public final static String META_INFO = "metaInfo";
    public final static String META_FORMAT = "metaFormat";
    public final static String METADATA = "metadata";
    public final static String RO_ID = "@id";
    public final static String FGDC_ID = "identifier";
    public final static String SIZE = "size";
    public final static String META_UPDATE_DATE = "metadataUpdateDate";
    public final static String DEPOSIT_DATE = "depositDate";
    public final static String FIXITY_FORMAT = "fixityFormat";
    public final static String FIXITY_VAL = "fixityValue";
    public final static String INFINITE = "inf";
    public final static String OBSOLETED_BY = "obsoletedBy";
    public final static String OBSOLETES = "obsoletes";

    //Synchronization schedule
    public static String SYNC_YEAR;
    public static String SYNC_MONTH;
    public static String SYNC_MDAY;
    public static String SYNC_WDAY;
    public static String SYNC_HOUR;
    public static String SYNC_MIN;
    public static String SYNC_SEC;

    //email credentials
    public static String emailUsername;
    public static String emailPassword;


    public static String dataonDbName;

    static {
        try {
            loadConfigurations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadConfigurations() throws IOException {
        InputStream inputStream =
                Constants.class.getResourceAsStream("./default.properties");

        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer);

        String result = writer.toString();
        String[] pairs = result.trim().split(
                "\n|\\=");


        for (int i = 0; i + 1 < pairs.length;) {
            String name = pairs[i++].trim();
            String value = pairs[i++].trim();

            if(name.equals("mongo.host")){
                mongoHost = value;
            }
            if(name.equals("mongo.port")){
                mongoPort = Integer.parseInt(value);
            }
            if(name.equals("dataone.db.name")){
                dataonDbName = value;
            }
            if (name.equals("node.identifier")) {
                NODE_IDENTIFIER = value;
            }
            if (name.equals("contact.subject")) {
                SUBJECT = value.replace("-", "=");
            }
            if (name.equals("base.url")) {
                BASE_URL = value;
            }

            //Synchronization schedule
            if (name.equals("year")) {
                SYNC_YEAR = value;
            }
            if (name.equals("month")) {
                SYNC_MONTH = value;
            }
            if (name.equals("day.of.month")) {
                SYNC_MDAY = value;
            }
            if (name.equals("day.of.week")) {
                SYNC_WDAY = value;
            }
            if (name.equals("hour")) {
                SYNC_HOUR = value;
            }
            if (name.equals("minute")) {
                SYNC_MIN = value;
            }
            if (name.equals("second")) {
                SYNC_SEC = value;
            }

            //email credentials
            if (name.equals("email.username")) {
                emailUsername = value;
            }
            if (name.equals("email.password")) {
                emailPassword = value;
            }
        }
    }
}