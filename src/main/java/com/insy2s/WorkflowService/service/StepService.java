package com.insy2s.WorkflowService.service;

import com.insy2s.WorkflowService.model.Step;
import com.insy2s.WorkflowService.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StepService extends JpaRepository<Step, Long> {
   // @Query("SELECT s FROM Step s WHERE s.workflow.id = :workflowId order by rank " )
   // List<Step> findAllByWorkflowId(@Param("workflowId") Long workflowId);
    //    @Query("SELECT s FROM Step s WHERE s.workflow.id = :workflowId AND s.role = :roleId ORDER BY s.rank")
   @Query("SELECT s FROM Step s JOIN s.role r WHERE s.workflow.id = :workflowId AND r = :roleId ORDER BY s.rank")
    List<Step> findAllStepsByWorkflowIdAndRoleId(@Param("workflowId") Long workflowId, @Param("roleId") Long roleId);

}
