package com.bilelhami.exam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String title ;
    private int timeLimit ;
    private int passingScore ;
    @NotBlank
    private String description ;

    @ManyToOne
    private User teacher ;

    @OneToMany

    private List<Question> questions ;

}
