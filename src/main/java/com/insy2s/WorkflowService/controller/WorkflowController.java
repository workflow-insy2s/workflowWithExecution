package com.insy2s.WorkflowService.controller;
import com.insy2s.WorkflowService.exception.ResourceNotFoundException;
import com.insy2s.WorkflowService.model.Workflow;
import com.insy2s.WorkflowService.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/workflows")
@CrossOrigin(origins = "http://localhost:4000/")
public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;

    @GetMapping
    public List<Workflow> getAllWorkflows(){
        return workflowService.findAll();
    }




    // build create workflow REST API
    @PostMapping
    public Workflow createWorkflow(@RequestBody Workflow workflow) {
        return workflowService.save(workflow);
    }



    // build get workflow by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Workflow> getWorkflowById(@PathVariable long id){
        Workflow workflow = workflowService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflow not exist with id:" + id));
        return ResponseEntity.ok(workflow);

    }





    // build update Workflow REST API
    @PutMapping("{id}")
    public ResponseEntity<Workflow> updateWorkflow(@PathVariable long id,@RequestBody Workflow workflowDetails) {
        Workflow updateWorkflow = workflowService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workflow not exist with id: " + id));

        updateWorkflow.setName(workflowDetails.getName());
        updateWorkflow.setDescription(workflowDetails.getDescription());
        //updateWorkflow.setResponsible(workflowDetails.getResponsible());




        workflowService.save(updateWorkflow);

        return ResponseEntity.ok(updateWorkflow);
    }



    // build delete Workflow REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWorkflow(@PathVariable long id){

        Workflow workflow = workflowService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflow not exist with id: " + id));

        workflowService.delete(workflow);
         String mes = "Workflow supprimé avec succès";

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Workflow supprimé avec succès");
        return ResponseEntity.ok(mes);

    }




}
