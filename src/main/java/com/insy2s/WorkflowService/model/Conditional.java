package com.insy2s.WorkflowService.model;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("conditional")
public class Conditional extends Step {

    private Long conditionId;

}
