package org.seadva.services.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;


public class Constants {

    public static String ezid_url;
    public static String doi_shoulder_prod;
    public static String doi_shoulder_test;
    public static String doi_username;
    public static String doi_password;

    static {

        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(Constants.class.getResourceAsStream("doi.properties")
                     , writer);

            String content = writer.toString();
            String[] pairs = content.trim().split(
                    "\n|\\=");

            for (int i = 0; i + 1 < pairs.length;) {
                String name = pairs[i++].trim();
                String value = pairs[i++].trim();
                if (name.equals("ezid.url")) {
                    ezid_url = value;
                }
                if (name.equals("doi.shoulder.prod")) {
                    doi_shoulder_prod = value;
                }
                if (name.equals("doi.shoulder.test")) {
                    doi_shoulder_test = value;
                }
                if (name.equals("doi.user")) {
                    doi_username = value;
                }
                if (name.equals("doi.pwd")) {
                    doi_password = value;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
