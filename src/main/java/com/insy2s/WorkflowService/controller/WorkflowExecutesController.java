package com.insy2s.WorkflowService.controller;
import com.insy2s.WorkflowService.exception.ResourceNotFoundException;
import com.insy2s.WorkflowService.model.Step;
import com.insy2s.WorkflowService.model.WorkflowExecutes;
import com.insy2s.WorkflowService.service.WorkflowExecutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/workflow/workflowExs")
@CrossOrigin(origins = "http://localhost:4000/")
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
    // save workflow to be executed




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


// pour les steps du workflow ordoner par le rank
@GetMapping("/workflow/stpes/{workflowId}")
public List<WorkflowExecutes> getAllStepsByWorkflowId(@PathVariable Long workflowId) {
    return workflowExecutesService.findAllByWorkflowId(workflowId);

}


//Add Steps executed by User
@PostMapping("/addSteps/{userId}")
public ResponseEntity<String> addStepsToWorkflowExecutes(@RequestBody List<Step> steps, @PathVariable Long userId) {
    for (Step step : steps) {
        WorkflowExecutes workflowExecutes = new WorkflowExecutes();
        workflowExecutes.setState("Ouvert"); // Remplacez par l'état initial souhaité
        workflowExecutes.setUser_id(userId);
//        workflowExecutes.setStart_date(LocalDateTime.now());
//        workflowExecutes.setEnd_date(LocalDateTime.now().plusHours(1)); // Exemple, ajustez si nécessaire
        workflowExecutes.setWorkflow(step.getWorkflow());
        workflowExecutes.setStep(step);

         workflowExecutesService.save(workflowExecutes);
    }


    return ResponseEntity.ok("All steps saved");
}

}
