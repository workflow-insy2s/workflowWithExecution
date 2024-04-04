package com.insy2s.WorkflowService.model;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("iterative")
public class Iterative extends Step{

    private Long exitRuleId;

}
