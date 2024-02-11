package com.bilelhami.exam.services;

import com.bilelhami.exam.models.Question;
import com.bilelhami.exam.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{
    private final QuestionRepository questionRepository ;
    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question update(Long id, Question question) throws ChangeSetPersister.NotFoundException {
        Optional<Question> theQuestion = questionRepository.findById(question.getId());
        if(theQuestion.isPresent()){
            Question questionToUpdate = theQuestion.get();
            questionToUpdate.setQuestion(question.getQuestion());
            questionToUpdate.setQuestionType(question.getQuestionType());
            questionToUpdate.setExplanation(question.getExplanation());
            questionToUpdate.setChoices(question.getChoices());
            questionToUpdate.setCorrectChoices(question.getCorrectChoices());
            return  questionRepository.save(questionToUpdate);
        }else {
            throw  new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }


    /*
    @Override
    public List<Question> getQuestionsForUser(Integer numOfQuestions, String subject) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return questionRepository.findBySubject(subject, pageable).getContent();
    }
    */


    @Override
    public List<Question> getAllQuestionsByExamId(long id) {
        return questionRepository.findAllByExam_Id(id);
    }
}
