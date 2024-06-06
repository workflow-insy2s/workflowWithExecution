package com.insy2s.WorkflowService.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddStepsRequest {
    private WorkflowEx workflowEx;
    private List<Step> steps;

}

