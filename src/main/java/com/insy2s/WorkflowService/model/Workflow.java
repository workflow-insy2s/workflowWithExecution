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
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate = LocalDateTime.now();


    // qui peut accedér a cette workflow ?
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> role;
//rolesId
    //private Long role;
//    @JsonIgnore
//    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Step> steps;







}
