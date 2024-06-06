package com.insy2s.WorkflowService.controller;

import com.insy2s.WorkflowService.exception.ResourceNotFoundException;
import com.insy2s.WorkflowService.model.StepEx;
import com.insy2s.WorkflowService.model.WorkflowEx;
import com.insy2s.WorkflowService.service.WorkflowExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/workflow/workflowExs")
@CrossOrigin(origins = "http://localhost:4000/")
public class WorkflowExController {
    @Autowired
    private WorkflowExService workflowExService;

    @GetMapping
    public List<WorkflowEx> getAllWorkflowEx(){return workflowExService.findAll();}

    @PostMapping
    public WorkflowEx createWorkflowEx(@RequestBody WorkflowEx workflowEx) {
        // Affecter la date actuelle au format souhaité à l'attribut end_date
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        workflowEx.setStart_date(parsedDateTime);
        return workflowExService.save(workflowEx);
    }


    // build get workflow by id REST API
    @GetMapping("{id}")
    public ResponseEntity<WorkflowEx> getWorkflowExById(@PathVariable long id){
        WorkflowEx workflowEx = workflowExService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflowEx not exist with id:" + id));
        return ResponseEntity.ok(workflowEx);

    }


    // build update Workflow REST API
    @PutMapping("{id}")
    public ResponseEntity<WorkflowEx> updateWorkflowEx(@PathVariable long id, @RequestBody WorkflowEx workflowExDetails) {
        WorkflowEx updateWorkflowEx = workflowExService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkflowEx not exist with id: " + id));

        updateWorkflowEx.setState(workflowExDetails.getState());


        // Affecter la date actuelle au format souhaité à l'attribut end_date
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        updateWorkflowEx.setEnd_date(parsedDateTime);

        workflowExService.save(updateWorkflowEx);

        return ResponseEntity.ok(updateWorkflowEx);
    }



    // build delete Workflow REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWorkflowEx(@PathVariable long id){

        WorkflowEx workflowEx = workflowExService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkflowEx not exist with id: " + id));

        workflowExService.delete(workflowEx);
        String mes = "WorkflowEx supprimé avec succès";

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Workflow supprimé avec succès");
        return ResponseEntity.ok(mes);

    }


    // get all workflowEx by workflowId order by date end
    @GetMapping("/workflowExByWorkflowId/{workflowId}")
    public List<WorkflowEx> getAllWorkflowExByWorkflowId(@PathVariable Long workflowId) {
        return workflowExService.findAllWorkflowExByWorkflowId(workflowId);

    }


}
