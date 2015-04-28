package org.seadva.services;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.seadva.services.util.IdMetadata;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

public class DataciteIdService {

    private String username;
    private String password;
    private String service;

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password =  password;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String createwithMd(Map metadata, boolean update) throws IOException {

        String metadataFile = writeMetadata(metadata,update);

        String cmd =
                "curl -u "+ username +":"+ password +" -X POST -H \"Content-Type:text/plain\" --data-binary @"+
                        metadataFile + " "+service;


        ByteArrayOutputStream stdout = executeCommand(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(stdout.toByteArray())));

        String output_line = null;

        String doiUrl = null;
        while((output_line = br.readLine()) != null)
        {

            if(output_line.contains("doi"))
            {

                doiUrl = output_line;
            }


        }

        return doiUrl;
    }

    private String writeMetadata(Map metadata, boolean update) throws IOException
    {
        String guid = UUID.randomUUID().toString();
        String tempFile = System.getProperty("java.io.tmpdir")+"/"+guid+"metadata.txt";

        File file = new File(tempFile);
        if(!file.exists())
            file.createNewFile();

        PrintWriter pw = new PrintWriter(tempFile);

        if(metadata.containsKey(IdMetadata.Metadata.TARGET))
            pw.println("_target: " + metadata.get(IdMetadata.Metadata.TARGET));

        if(metadata.containsKey(IdMetadata.Metadata.TITLE))
            pw.println("datacite.title: " + metadata.get(IdMetadata.Metadata.TITLE));
        else if(!update)
            pw.println("datacite.title: " +"(:unav)");


        if(metadata.containsKey(IdMetadata.Metadata.CREATOR))
            pw.println("datacite.creator: " + metadata.get(IdMetadata.Metadata.CREATOR));
        else if(!update)
            pw.println("datacite.creator: " +"(:unav)");


        if(metadata.containsKey(IdMetadata.Metadata.PUBDATE))
            pw.println("datacite.publicationyear: " + metadata.get(IdMetadata.Metadata.PUBDATE));
        else if(!update)
            pw.println("datacite.publicationyear: " + "(:unav)");

        if(metadata.containsKey(IdMetadata.Metadata.PUBLISHER))
            pw.println("datacite.publisher: " + metadata.get(IdMetadata.Metadata.PUBLISHER));
        else if(!update)
            pw.println("datacite.publisher: " + "(:unav)");

        pw.flush();
        pw.close();

        return tempFile;

    }

    ByteArrayOutputStream executeCommand(String command) throws IOException {
        CommandLine cmdLine = CommandLine.parse(command);
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        PumpStreamHandler psh = new PumpStreamHandler(stdout);

        executor.setStreamHandler(psh);

        int exitValue = executor.execute(cmdLine);
        return  stdout;
    }
}
