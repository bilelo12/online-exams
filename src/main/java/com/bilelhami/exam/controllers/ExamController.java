package com.bilelhami.exam.controllers;


import com.bilelhami.exam.models.Exam;
import com.bilelhami.exam.models.Question;
import com.bilelhami.exam.services.IExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/exams")
@RequiredArgsConstructor
public class ExamController {

    private final IExamService examService ;

    @GetMapping("")
    public ResponseEntity<List<Exam>> getAllExams(){
        List<Exam> exams = examService.listExams();
        return ResponseEntity.ok(exams);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Exam> exam = examService.getExamById(id);
        if (exam.isPresent()){
            return ResponseEntity.ok(exam.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
    @PostMapping("")
    public ResponseEntity<Exam> createExam(@Valid @RequestBody Exam exam){
        Exam createdExam = examService.create(exam);
        return ResponseEntity.status(CREATED).body(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateQuestion(
            @PathVariable Long id, @RequestBody Exam exam) throws ChangeSetPersister.NotFoundException {
        Exam updatedExam = examService.update(id, exam);
        return ResponseEntity.ok(updatedExam);
    }
}
