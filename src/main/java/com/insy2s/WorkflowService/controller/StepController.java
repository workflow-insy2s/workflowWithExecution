package com.insy2s.WorkflowService.controller;
import com.insy2s.WorkflowService.exception.ResourceNotFoundException;


import com.insy2s.WorkflowService.model.*;
import com.insy2s.WorkflowService.service.StepService;
import com.insy2s.WorkflowService.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/workflow/steps")
@CrossOrigin(origins = "http://localhost:4000/")
public class StepController {

    @Autowired
    private StepService stepService;
    @Autowired
    private WorkflowService workflowService;

    @GetMapping
    public List<Step> getAllUSteps(){
        return stepService.findAll();
    }




    // build create Step REST API
    @PostMapping
    public Step createStep(@RequestBody Step step) {
        return stepService.save(step);
    }

    //



    // build get step by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Step> getStepById(@PathVariable long id){
        Step step = stepService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("step not exist with id:" + id));
        return ResponseEntity.ok(step);

    }





    // build update Step REST API
    @PutMapping("{id}")
    public ResponseEntity<Step> updateStep(@PathVariable long id,@RequestBody Step stepDetails) {
        Step updateStep = stepService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Step not exist with id: " + id));

        updateStep.setDescription(stepDetails.getDescription());
        updateStep.setName(stepDetails.getName());
       // updateStep.setIdStepEntry(stepDetails.getIdStepEntry());
       // updateStep.setIdStepExit(stepDetails.getIdStepExit());
        //updateStep.setResult(stepDetails.getResult());


        updateStep.setWorkflow(stepDetails.getWorkflow());
        updateStep.setCreationDate(stepDetails.getCreationDate());

        stepService.save(updateStep);

        return ResponseEntity.ok(updateStep);
    }



    // build delete Step REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStep(@PathVariable long id){

        Step step = stepService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Step not exist with id: " + id));

        stepService.delete(step);



        String mes = "Step supprimé avec succès";

        return ResponseEntity.ok(mes);

    }

    //for conditional

    @PostMapping("/conditional")
    public ResponseEntity<?> createConditionalStep(@RequestBody Conditional conditionalStep) {

        stepService.save(conditionalStep);
        String mes = "Step conditional Ajouter avec succès";

        return ResponseEntity.ok(mes);
    }
    @PostMapping("/iterative")
    public ResponseEntity<?> createIterativeStep(@RequestBody Iterative iterativeStep) {

        stepService.save(iterativeStep);
        String mes = "Step iterative Ajouter avec succès";

        return ResponseEntity.ok(mes);
    }



    // build create Step REST API with workflow ID
    @PostMapping("/addStepInWorkflow/{workflowId}")
    public ResponseEntity<Step> createStepWithWorkflowId(@PathVariable Long workflowId, @RequestBody Step step) {
        // Rechercher le workflow correspondant à l'ID
        Workflow workflow = workflowService.findById(workflowId)
                .orElseThrow(() -> new ResourceNotFoundException("Workflow not exist with id:" + workflowId));

        // Associer le workflow au Step
        step.setWorkflow(workflow);

        // Enregistrer le Step
        Step createdStep = stepService.save(step);

        return ResponseEntity.ok(createdStep);
    }


// build create Conditional and Iterative REST API with workflow ID

    @PostMapping("/conditional/workflow/{workflowId}")
    public ResponseEntity<Conditional> createConditionalStepWithWorkflowId(@PathVariable Long workflowId, @RequestBody Conditional conditionalStep) {
        // Rechercher le workflow correspondant à l'ID
        Workflow workflow = workflowService.findById(workflowId)
                .orElseThrow(() -> new ResourceNotFoundException("Workflow not exist with id:" + workflowId));

        // Associer le workflow au Conditional Step
        conditionalStep.setWorkflow(workflow);

        // Enregistrer le Conditional Step
        Conditional createdConditionalStep = (Conditional) stepService.save(conditionalStep);

        return ResponseEntity.ok(createdConditionalStep);
    }

    @PostMapping("/iterative/workflow/{workflowId}")
    public ResponseEntity<Iterative> createIterativeStepWithWorkflowId(@PathVariable Long workflowId, @RequestBody Iterative iterativeStep) {
        // Rechercher le workflow correspondant à l'ID
        Workflow workflow = workflowService.findById(workflowId)
                .orElseThrow(() -> new ResourceNotFoundException("Workflow not exist with id:" + workflowId));

        // Associer le workflow au Iterative Step
        iterativeStep.setWorkflow(workflow);

        // Enregistrer le Iterative Step
        Iterative createdIterativeStep = (Iterative) stepService.save(iterativeStep);

        return ResponseEntity.ok(createdIterativeStep);
    }


    // Obtenir tous les Steps par ID de Workflow
//    @GetMapping("/workflow/{workflowId}")
//    public List<Step> getAllStepsByWorkflowId(@PathVariable Long workflowId) {
//        return stepService.findAllByWorkflowId(workflowId);
//
//    }


// get all steps by workflowId and Role Id
@GetMapping("/workflow/{workflowId}/role/{roleId}")
public List<Step> getStepsByWorkflowIdAndRoleId(@PathVariable Long workflowId, @PathVariable Long roleId) {
    return stepService.findAllStepsByWorkflowIdAndRoleId(workflowId, roleId);

}



}
