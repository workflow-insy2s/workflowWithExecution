package com.insy2s.WorkflowService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkflowEx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    private String state;

    // In cases where the execution is not completed, save the id of last stepEx executed
    private Long level;

    private Long user_id;


    private LocalDateTime start_date;


    private LocalDateTime end_date ;


    // Relation MANY TO ONE with Workflow
    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;





    // Relation MANY TO ONE  with Step

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "step_id")
//    private Step step;



//les step na5thohom mina
    //select steps where workflow = id and

}
