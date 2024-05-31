package com.insy2s.WorkflowService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "step_type", discriminatorType = DiscriminatorType.STRING)
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate = LocalDateTime.now();

    //Liste de regles d'entre et liste de regle de sortie
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> exitRulesIds;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> entryRulesId;

    // Role qui responsable a Step
  //  private Long role;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> role;
    //many to one step
   // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;



//order
    private Long rank ;

//le etape précédente pour le presentation de graphe de workflow
    //private Long previous_step;

}
