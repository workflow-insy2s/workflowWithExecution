package com.insy2s.WorkflowService.service;

import com.insy2s.WorkflowService.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepService extends JpaRepository<Step, Long> {
}
