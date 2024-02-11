package com.bilelhami.exam.services;

import com.bilelhami.exam.models.Question;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    Question create(Question question);
    List<Question> getAllQuestions();
    Optional<Question> getQuestionById(Long id);

    Question update(Long id , Question question) throws ChangeSetPersister.NotFoundException;
    void delete(Long id);
    //List<Question> getQuestionsForUser(Integer numOfQuestions, String subject);

    List<Question> getAllQuestionsByExamId(long id);
}
