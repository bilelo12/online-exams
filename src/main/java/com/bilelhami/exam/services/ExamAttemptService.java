package com.bilelhami.exam.services;
import com.bilelhami.exam.models.ExamAttempt;
import com.bilelhami.exam.repositories.ExamAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamAttemptService implements IExamAttemptService {

    private final ExamAttemptRepository examAttemptRepository;
    @Override
    public ExamAttempt create(ExamAttempt examAttempt) {
        return examAttemptRepository.save(examAttempt);
    }

    @Override
    public List<ExamAttempt> listExamAttempts() {
        return examAttemptRepository.findAll();
    }

    @Override
    public Optional<ExamAttempt> getExamAttemptById(long id) {
        return examAttemptRepository.findById(id);
    }
}
