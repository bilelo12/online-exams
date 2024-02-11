package com.bilelhami.exam.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String studentEmail ;
    private String token = UUID.randomUUID().toString().replaceAll("-","");
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam ;
    private boolean isUsed = false ;

}
