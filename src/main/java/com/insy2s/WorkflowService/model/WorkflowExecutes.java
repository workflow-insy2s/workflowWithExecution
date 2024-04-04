package com.insy2s.WorkflowService.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkflowExecutes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;

    private Long user_id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime start_date = LocalDateTime.now();

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime end_date = LocalDateTime.now();


    // Relation MANY TO ONE with Workflow
    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;





    // Relation MANY TO ONE  with Step
    @ManyToOne
    @JoinColumn(name = "step_id")
    private Step step;





}
