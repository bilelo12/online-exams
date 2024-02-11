package com.bilelhami.exam.repositories;

import com.bilelhami.exam.models.ExamAttempt;
import com.bilelhami.exam.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt,Long> {
}
