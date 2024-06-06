package com.insy2s.WorkflowService.service;

import com.insy2s.WorkflowService.model.WorkflowEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkflowExService extends JpaRepository<WorkflowEx,Long> {
    @Query("SELECT s FROM WorkflowEx s WHERE s.workflow.id = :workflowId order by s.end_date" )
    List<WorkflowEx> findAllWorkflowExByWorkflowId(@Param("workflowId") Long workflowId);
}
