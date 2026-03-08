package com.cadastro.fabiano.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "form_submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_template_id")
    private FormTemplate template;

    @ElementCollection
    @CollectionTable(name = "form_submission_values", joinColumns = @JoinColumn(name = "submission_id"))
    @MapKeyColumn(name = "field_label")
    @Column(name = "field_value")
    private Map<String, String> values;

    private LocalDateTime createdAt = LocalDateTime.now();
}