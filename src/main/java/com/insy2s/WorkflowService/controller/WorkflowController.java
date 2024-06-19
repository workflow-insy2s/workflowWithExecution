package com.insy2s.WorkflowService.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insy2s.WorkflowService.exception.ResourceNotFoundException;
import com.insy2s.WorkflowService.model.Step;
import com.insy2s.WorkflowService.model.Workflow;
import com.insy2s.WorkflowService.service.StepService;
import com.insy2s.WorkflowService.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/workflow")
@CrossOrigin(origins = "http://localhost:4000/")

public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private StepService stepService;




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
        updateWorkflow.setRole(workflowDetails.getRole());
        updateWorkflow.setStatus(workflowDetails.getStatus());





        workflowService.save(updateWorkflow);

        return ResponseEntity.ok(updateWorkflow);
    }



    // build delete Workflow REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkflow(@PathVariable long id){

        Workflow workflow = workflowService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflow not exist with id: " + id));

        workflowService.delete(workflow);
         String mes = "Workflow supprimé avec succès";

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Workflow supprimé avec succès");
        return ResponseEntity.ok(mes);

    }

    //delete workflow with Step
    @DeleteMapping("/withStep/{id}")
    @Transactional
    public ResponseEntity<String> deleteWorkflowwithStep(@PathVariable long id){

        Workflow workflow = workflowService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("workflow not exist with id: " + id));


        // Supprimer toutes les étapes associées
        stepService.deleteByWorkflow(workflow);

        workflowService.delete(workflow);
        String mes = "Workflow supprimé avec succès";

        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Workflow supprimé avec succès");
        return ResponseEntity.ok(mes);

    }



    //getAllWorkflow By Step Id
    @GetMapping("/role/{role}")
    public List<Workflow> getAllWorkflowByRole(@PathVariable Long role) {
        // Utilisez le service pour récupérer les workflows en fonction de role_id
        return workflowService.findAllByRole(role);
    }



    // Obtenir tous les steps d'un workflow donné




    // les methode pour l'execution de workflow

//    @GetMapping("/executeRuleEntry")
//    public boolean executeWorkflow(@RequestBody Step step) {
//        List<Boolean> resultat = new ArrayList<>(); // Initialisation de la liste resultat
//
//        List<Long> entryRulesId = step.getEntryRulesId();
//        for (Long ruleId : entryRulesId) {
//            String id = ruleId.toString();
//            String result = consumeRuleApi(id); // Appel de la méthode consumeRuleApi et stockage du résultat
//            boolean boolValue = Boolean.parseBoolean(result);
//            resultat.add(boolValue); // Ajout du résultat à la liste resultat
//        }
//
//        boolean resultatFinal = true;
//        for (Boolean res : resultat) {
//            resultatFinal = resultatFinal && res; // Operation "et" entre tous les elements de resultat
//        }
//
//        return resultatFinal; // Retourner le résultat final
//    }
//
//
//
//
//
//    // Nouvelle méthode pour consommer l'API RuleService
//    //
//    public String consumeRuleApi(String id) {
//        // URL de l'API
//        String apiUrl = "http://localhost:7080/api/v1/rules/relation2eme/"+id;
//
//        try {
//        // Utilisation de RestTemplate pour faire une requête GET à l'API externe
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//
//        // Vérifier si la requête a réussi (code 2xx)
//        if (response.getStatusCode().is2xxSuccessful()) {
//            // Récupérer le contenu de la réponse
//            String apiContent = response.getBody();
//            return apiContent;
//        } else {
//            // Gérer les erreurs si la requête n'a pas réussi
//            return "Erreur lors de la récupération des règles depuis le RuleService.";
//        }
//        } catch (Exception e) {
//            // Gestion des exceptions
//            return "Une erreur s'est produite lors de l'appel à l'API RuleService.";
//        }
//    }


}
