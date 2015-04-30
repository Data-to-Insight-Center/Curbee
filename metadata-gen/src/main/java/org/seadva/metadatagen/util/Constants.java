package org.seadva.metadatagen.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public class Constants {

    public static String sourceTerm = "http://www.loc.gov/METS/FLocat";
    public static String rosystemURL;
    public static String dcsBaseURL;
    public static String bagPath;

    public static Map<String, String> json_map = new HashMap<String, String>();

    static {

        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(Constants.class.getResourceAsStream("./Config.properties")
                    , writer);

            String content = writer.toString();
            String[] pairs = content.trim().split(
                    "\n|\\=");

            for (int i = 0; i + 1 < pairs.length;) {
                String name = pairs[i++].trim();
                String value = pairs[i++].trim();
                if (name.equals("rosystem.url")) {
                    rosystemURL = value;
                }
                if (name.equals("dcs.baseurl")) {
                    dcsBaseURL = value;
                }
                if (name.equals("bag.path")) {
                    bagPath = value;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        for (int i = 0; i < 3 ; i++) {
            StringWriter writer = new StringWriter();
            try {
                IOUtils.copy(Constants.class.getResourceAsStream("./../util/test_202"+i+".json")
                        , writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String content = writer.toString();
            json_map.put(dcsBaseURL+"entity/202"+i,content);
        }
    }

}
