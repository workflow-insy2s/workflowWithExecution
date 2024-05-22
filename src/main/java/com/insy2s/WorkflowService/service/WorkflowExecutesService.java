package com.insy2s.WorkflowService.service;

import com.insy2s.WorkflowService.model.Step;
import com.insy2s.WorkflowService.model.WorkflowExecutes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkflowExecutesService extends JpaRepository<WorkflowExecutes,Long> {
    @Query("SELECT s FROM WorkflowExecutes s WHERE s.workflow.id = :workflowId order by s.step.rank" )
     List<WorkflowExecutes> findAllByWorkflowId(@Param("workflowId") Long workflowId);
}
