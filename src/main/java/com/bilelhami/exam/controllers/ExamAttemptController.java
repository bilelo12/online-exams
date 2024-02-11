package com.bilelhami.exam.controllers;


import com.bilelhami.exam.models.ExamAttempt;
import com.bilelhami.exam.services.IExamAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/exam-attempts")
@RequiredArgsConstructor
public class ExamAttemptController {
    private final IExamAttemptService examAttemptService ;

    @GetMapping("")
    public ResponseEntity<List<ExamAttempt>> getAll(){
        List<ExamAttempt> examAttempts = examAttemptService.listExamAttempts();
        return ResponseEntity.ok(examAttempts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExamAttempt> getExamAttemptById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<ExamAttempt> examAttempt =examAttemptService.getExamAttemptById(id);
        if (examAttempt.isPresent()){
            return ResponseEntity.ok(examAttempt.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
    @PostMapping("")
    public ResponseEntity<ExamAttempt> createExamAttempt(@Valid @RequestBody ExamAttempt examAttempt){
        ExamAttempt createdExamAttempt = examAttemptService.create(examAttempt);
        return ResponseEntity.status(CREATED).body(createdExamAttempt);
    }
}
