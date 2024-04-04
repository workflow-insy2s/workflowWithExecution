package com.insy2s.WorkflowService.service;

import com.insy2s.WorkflowService.model.WorkflowExecutes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowExecutesService extends JpaRepository<WorkflowExecutes,Long> {
}
