package com.insy2s.WorkflowService.service;

import com.insy2s.WorkflowService.model.StepEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StepExService extends JpaRepository<StepEx,Long> {
    @Query("SELECT s FROM StepEx s WHERE s.workflowEx = :workflowId order by s.step.rank" )
     List<StepEx> findAllByWorkflowId(@Param("workflowId") Long workflowId);
}
