package com.insy2s.WorkflowService.controller;
import com.insy2s.WorkflowService.exception.ResourceNotFoundException;
import com.insy2s.WorkflowService.model.Workflow;
import com.insy2s.WorkflowService.model.WorkflowExecutes;
import com.insy2s.WorkflowService.service.WorkflowExecutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/workflowExs")
public class WorkflowExecutesController {
    @Autowired
    private WorkflowExecutesService workflowExecutesService;

    @GetMapping
    public List<WorkflowExecutes> getAllWorkflowExecutes(){return workflowExecutesService.findAll();}


    // build create workflow REST API
    @PostMapping
    public WorkflowExecutes createWorkflowExecutes(@RequestBody WorkflowExecutes workflowExecutes) {
        return workflowExecutesService.save(workflowExecutes);
    }



    // build get workflow by id REST API
    @GetMapping("{id}")
    public ResponseEntity<WorkflowExecutes> getWorkflowExecutesById(@PathVariable long id){
        WorkflowExecutes workflowExecutes = workflowExecutesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflowExecutes not exist with id:" + id));
        return ResponseEntity.ok(workflowExecutes);

    }





    // build update Workflow REST API
    @PutMapping("{id}")
    public ResponseEntity<WorkflowExecutes> updateWorkflowExecutes(@PathVariable long id,@RequestBody WorkflowExecutes workflowExecutesDetails) {
        WorkflowExecutes updateWorkflowExecutes = workflowExecutesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflowExecutes not exist with id: " + id));

        //updateWorkflowExecutes.setCurrentStep(workflowExecutesDetails.getCurrentStep());
        updateWorkflowExecutes.setState(workflowExecutesDetails.getState());
        //updateWorkflowExecutes.setEndDate(workflowExecutesDetails.getEndDate());
        //updateWorkflowExecutes.setStartDate(workflowExecutesDetails.getStartDate());





        workflowExecutesService.save(updateWorkflowExecutes);

        return ResponseEntity.ok(updateWorkflowExecutes);
    }



    // build delete Workflow REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWorkflowExecutes(@PathVariable long id){

        WorkflowExecutes workflowExecutes = workflowExecutesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkflowExecutes not exist with id: " + id));

        workflowExecutesService.delete(workflowExecutes);
        String mes = "Workflow supprimé avec succès";

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Workflow supprimé avec succès");
        return ResponseEntity.ok(mes);

    }




}
