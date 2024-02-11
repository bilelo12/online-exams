package com.bilelhami.exam.repositories;

import com.bilelhami.exam.models.Exam;
import com.bilelhami.exam.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
