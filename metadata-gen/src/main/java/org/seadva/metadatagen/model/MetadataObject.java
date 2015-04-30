package org.seadva.metadatagen.model;

import java.util.*;


public class MetadataObject {

    private Map<String, List<String>> metadataMap;
    private List<String> collections;
    private Map<String, Map<String, List<String>>> files;

    public MetadataObject() {
        metadataMap = new HashMap<String, List<String>>();
        collections = new ArrayList<String>();
        files = new HashMap<String, Map<String, List<String>>>();
    }

    public Map<String, List<String>> getMetadataMap() {
        return metadataMap;
    }

    public void setMetadataMap(Map<String, List<String>> metadataMap) {
        this.metadataMap = metadataMap;
    }

    public List<String> getCollections() {
        return collections;
    }

    public void setCollections(List<String> collections) {
        this.collections = collections;
    }

    public Map<String, Map<String, List<String>>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Map<String, List<String>>> files) {
        this.files = files;
    }
}
