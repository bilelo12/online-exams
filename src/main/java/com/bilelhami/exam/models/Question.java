package com.bilelhami.exam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @NotBlank
    private String question ;

    @NotBlank
    private String explanation ;

    @NotBlank
    private String questionType ;

    @ElementCollection
    private List<String> choices ;

    @ElementCollection
    private List<String> correctChoices ;

    @ManyToOne
    @JsonIgnore
    private Exam exam ;

}
