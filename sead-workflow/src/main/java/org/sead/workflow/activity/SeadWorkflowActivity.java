package org.sead.workflow.activity;

import java.util.HashMap;

/**
 * Workflow Activity interface
 */
public interface SeadWorkflowActivity {

    /**
     * Executes the activity logic
     */
    public void execute();

    public void addParam(String key, String value);

    public void setName(String name);

}
