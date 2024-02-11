package com.bilelhami.exam.repositories;

import com.bilelhami.exam.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    //@Query("SELECT DISTINCT q.subject FROM Question q")
    //List<String> findDistinctSubject();
    //Page<Question> findBySubject(String subject, Pageable pageable);



    List<Question> findAllByExam_Id(long examId);
}
