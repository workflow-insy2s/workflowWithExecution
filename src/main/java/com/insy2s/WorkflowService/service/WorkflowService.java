package com.insy2s.WorkflowService.service;
import com.insy2s.WorkflowService.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowService extends JpaRepository<Workflow, Long>{
}
