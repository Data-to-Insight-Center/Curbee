package org.sead.workflow.activity;

import java.util.HashMap;

public abstract class AbstractWorkflowActivity implements SeadWorkflowActivity {

    // name of the activity
    public String activityName = null;
    // parameter map for the activity
    public HashMap<String, String> params = new HashMap<String, String>();

    @Override
    public abstract void execute();

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public void setName(String name) {
        activityName = name;
    }
}
