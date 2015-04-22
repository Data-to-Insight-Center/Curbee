package org.sead.workflow.config;

import org.sead.workflow.activity.SeadWorkflowActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Holds the Workflow configuration. This class should be populated by reading the
 * sead-wf.xml config file
 */
public class SeadWorkflowConfig {

    // set of config level parameters
    private HashMap<String, String> workflowParams = new HashMap<String, String>();
    // list of activities
    private List<SeadWorkflowActivity> activities = new ArrayList<SeadWorkflowActivity>();

    public void addParam(String key, String value) {
        workflowParams.put(key, value);
    }

    public String getParamValue(String key) {
        return workflowParams.get(key);
    }

    public void addActivity(SeadWorkflowActivity activity) {
        activities.add(activity);
    }

    public List<SeadWorkflowActivity> getActivities() {
        return activities;
    }

}
