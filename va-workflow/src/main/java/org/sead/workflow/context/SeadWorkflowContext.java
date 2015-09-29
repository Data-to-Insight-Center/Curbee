/*
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
 * @author isuriara@indiana.edu
 */

package org.sead.workflow.context;

import java.util.HashMap;

/**
 * Holds runtime information of a workflow invocation. Each activity in the workflow
 * can use the context instance to retrieve or store shared information.
 */
public class SeadWorkflowContext {

    // registry collection id
    private String collectionId = null;

    // set of config level parameters
    private HashMap<String, String> properties = new HashMap<String, String>();

    // object that holds the project space configurations

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public void updateProperty(String key, String value) {
        properties.put(key, value);
    }

}
