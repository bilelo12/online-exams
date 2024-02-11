package com.bilelhami.exam.controllers;

import com.bilelhami.exam.models.Question;
import com.bilelhami.exam.services.IQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @PostMapping("")
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question){
        Question createdQuestion = questionService.create(question);
        return ResponseEntity.status(CREATED).body(createdQuestion);
    }

    @GetMapping("")
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Question> theQuestion = questionService.getQuestionById(id);
        if (theQuestion.isPresent()){
            return ResponseEntity.ok(theQuestion.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<List<Question>> getAllQuestionsByExamId(@PathVariable Long id){
        List<Question> questions = questionService.getAllQuestionsByExamId(id);
        return ResponseEntity.ok(questions);
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Long id, @RequestBody Question question) throws ChangeSetPersister.NotFoundException {
        Question updatedQuestion = questionService.update(id, question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
