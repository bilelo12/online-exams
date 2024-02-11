package com.bilelhami.exam.services;

import com.bilelhami.exam.models.Exam;
import com.bilelhami.exam.repositories.ExamRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExamService implements  IExamService{

    private final ExamRepository examRepository ;
    @Override
    public Exam create(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> listExams() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> getExamById(long id) {
        return examRepository.findById(id);
    }

    @Override
    public Exam update(long id, Exam exam) throws ChangeSetPersister.NotFoundException {
        Optional<Exam> theExam = examRepository.findById(id);
        if(theExam.isPresent()){
            Exam examToUpdate = theExam.get();
            examToUpdate.setTitle(exam.getTitle());
            examToUpdate.setDescription( exam.getDescription());
            examToUpdate.setQuestions(exam.getQuestions());
            examToUpdate.setTimeLimit(exam.getTimeLimit());
            examToUpdate.setPassingScore(exam.getPassingScore());
            return examRepository.save(examToUpdate);
        }else {
            throw  new ChangeSetPersister.NotFoundException();
        }

    }
}
