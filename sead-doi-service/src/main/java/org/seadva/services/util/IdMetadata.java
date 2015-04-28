package org.seadva.services.util;

import java.util.Map;

public interface IdMetadata {


    public Map<Metadata,String> getMetadata();

    public void setMetadata(Map<Metadata, String> mdata);

    public enum Metadata{

        TITLE("title"),CREATOR("creator"),TARGET("target"),PUBLISHER("publisher"),PUBDATE("pubdate");

        private final String prefix;

        Metadata(String prefix) {
            this.prefix = prefix;
        }


    }

}
