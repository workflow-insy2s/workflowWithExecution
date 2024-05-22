package com.insy2s.WorkflowService.service;
import com.insy2s.WorkflowService.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowService extends JpaRepository<Workflow, Long>{
    List<Workflow> findAllByRole(Long role);

}
