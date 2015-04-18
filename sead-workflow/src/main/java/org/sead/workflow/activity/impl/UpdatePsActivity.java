package org.sead.workflow.activity.impl;

import org.sead.workflow.activity.AbstractWorkflowActivity;

/**
 * Responsible for updating Project Spaces after publishing an RO
 */
public class UpdatePsActivity extends AbstractWorkflowActivity {

    @Override
    public void execute() {
        System.out.println("Executing activity : " + activityName);
    }
}
