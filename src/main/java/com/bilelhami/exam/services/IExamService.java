package com.bilelhami.exam.services;

import com.bilelhami.exam.models.Exam;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IExamService {
    Exam create(Exam exam);
    List<Exam> listExams();
    Optional<Exam> getExamById(long id );
    Exam update(long id ,Exam exam) throws ChangeSetPersister.NotFoundException;

}
