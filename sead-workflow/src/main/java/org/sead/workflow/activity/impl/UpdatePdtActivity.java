package org.sead.workflow.activity.impl;

import org.sead.workflow.activity.AbstractWorkflowActivity;

/**
 * Responsible for updating PDT with the published RO details
 */
public class UpdatePdtActivity extends AbstractWorkflowActivity {

    @Override
    public void execute() {
        System.out.println("Executing activity : " + activityName);
    }
}
