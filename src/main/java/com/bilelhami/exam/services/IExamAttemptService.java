package com.bilelhami.exam.services;

import com.bilelhami.exam.models.ExamAttempt;

import java.util.List;
import java.util.Optional;

public interface IExamAttemptService {

    ExamAttempt create(ExamAttempt examAttempt);
    List<ExamAttempt> listExamAttempts();
    Optional<ExamAttempt> getExamAttemptById(long id );
}
