package com.insy2s.WorkflowService.controller;
import com.insy2s.WorkflowService.exception.ResourceNotFoundException;
import com.insy2s.WorkflowService.model.Step;
import com.insy2s.WorkflowService.model.StepEx;
import com.insy2s.WorkflowService.model.WorkflowEx;
import com.insy2s.WorkflowService.service.StepExService;
import com.insy2s.WorkflowService.service.StepService;
import com.insy2s.WorkflowService.service.WorkflowExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@RestController
@RequestMapping("/api/workflow/stepExs")
@CrossOrigin(origins = "http://localhost:4000/")
public class StepExController {
    @Autowired
    private StepExService stepExService;
    @Autowired
    private WorkflowExService workflowExService;
    @Autowired
    private StepService stepService;


    @GetMapping
    public List<StepEx> getAllStepEx(){return stepExService.findAll();}


    // build create workflow REST API
    @PostMapping
    public StepEx createStepEx(@RequestBody StepEx stepEx) {

        // Affecter la date actuelle au format souhaité à l'attribut end_date
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        stepEx.setStart_date(parsedDateTime);
        return stepExService.save(stepEx);
    }
    // save workflow to be executed




    // build get workflow by id REST API
    @GetMapping("{id}")
    public ResponseEntity<StepEx> getStepExById(@PathVariable long id){
        StepEx stepEx = stepExService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StepEx not exist with id:" + id));
        return ResponseEntity.ok(stepEx);

    }





    // build update Workflow REST API
    @PutMapping("{id}")
    public ResponseEntity<StepEx> updateStepEx(@PathVariable long id,@RequestBody StepEx stepExDetails) {
        StepEx updateStepEx = stepExService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StepEx not exist with id: " + id));

        updateStepEx.setState(stepExDetails.getState());

        // Affecter la date actuelle au format souhaité à l'attribut end_date
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        updateStepEx.setEnd_date(parsedDateTime);






        stepExService.save(updateStepEx);

        return ResponseEntity.ok(updateStepEx);
    }



    // build delete Workflow REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStepEx(@PathVariable long id){

        StepEx stepEx = stepExService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StepEx not exist with id: " + id));

        stepExService.delete(stepEx);
        String mes = "StepEx supprimé avec succès";

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Workflow supprimé avec succès");
        return ResponseEntity.ok(mes);

    }


// pour les steps du workflow ordoner par le rank
@GetMapping("/stpeExsByIdWorkflowEx/{workflowExId}")
public List<StepEx> getAllStepsByWorkflowId(@PathVariable Long workflowExId) {
    return stepExService.findAllByWorkflowId(workflowExId);

}


//Add Steps executed by User
@PostMapping("/addStepsInStepEx/workflowEx/{workflowExecutionId}/user/{userId}")
public ResponseEntity<String> addStepsToStepEx(@RequestBody List<Step> steps, @PathVariable Long workflowExecutionId, @PathVariable Long userId) {

    for (Step step : steps) {
        StepEx stepEx = new StepEx();
        stepEx.setState("Ouvert");
        stepEx.setUser_id(userId);
        stepEx.setWorkflowEx(workflowExecutionId);
        stepEx.setStep(step);
        stepExService.save(stepEx);

    }





    return ResponseEntity.ok("All steps saved");
}





}
