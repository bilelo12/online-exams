package com.bilelhami.exam.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class ExamAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam ;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student ;

    @CreationTimestamp
    private  Date startAt  ;
    private  Date finishAt  ;
    private  double score ;

}
