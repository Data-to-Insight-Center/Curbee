package org.sead.workflow.activity.impl;

import org.sead.workflow.activity.AbstractWorkflowActivity;

/**
 * Responsible for generating metadata standards like ORE, SIP etc.
 */
public class GenerateMetadataActivity extends AbstractWorkflowActivity {

    @Override
    public void execute() {
        System.out.println("Executing activity : " + activityName);
    }
}
