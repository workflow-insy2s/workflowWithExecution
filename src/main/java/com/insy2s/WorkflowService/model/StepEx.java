package com.insy2s.WorkflowService.model;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StepEx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;

    private Long user_id;


    private LocalDateTime start_date;

    private LocalDateTime end_date;


    // Relation MANY TO ONE with Workflow
//    @ManyToOne
//    @JoinColumn(name = "workflowEx_id")
//    private WorkflowEx workflowEx;
    private Long workflowEx;





    // Relation MANY TO ONE  with Step

    @ManyToOne
    @JoinColumn(name = "step_id")
    private Step step;





}
