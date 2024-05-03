package com.insy2s.WorkflowService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepDto {
    private Long id;

    private String name;

    private String description;

    private Long workflowId;
}

