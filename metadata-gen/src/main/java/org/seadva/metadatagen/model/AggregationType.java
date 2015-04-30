package org.seadva.metadatagen.model;

/**
 * File or collection types
 */
public enum AggregationType {
    FILE ("File"), COLLECTION ("Collection");


    private AggregationType(final String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }

    public String fromString() {

        return text;
    }
}
