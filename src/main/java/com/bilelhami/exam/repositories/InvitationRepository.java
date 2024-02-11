package com.bilelhami.exam.repositories;

import com.bilelhami.exam.models.Invitation;
import com.bilelhami.exam.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {


    List<Invitation> findAllByExam_Id(long examId);
}
