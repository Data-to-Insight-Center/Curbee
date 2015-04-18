package org.sead.workflow.activity.impl;

import org.sead.workflow.activity.AbstractWorkflowActivity;

/**
 * Responsible for persisting the RO in RO subsystem
 */
public class PersistROActivity extends AbstractWorkflowActivity {

    @Override
    public void execute() {
        System.out.println("Executing activity : " + activityName);
    }

}
