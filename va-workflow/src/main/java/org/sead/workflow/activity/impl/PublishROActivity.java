package org.sead.workflow.activity.impl;

import org.sead.workflow.activity.AbstractWorkflowActivity;

/**
 * Responsible for publishing the RO by calling Matchmaker
 */
public class PublishROActivity extends AbstractWorkflowActivity {

    @Override
    public void execute() {
        System.out.println("Executing activity : " + activityName);
    }
}
