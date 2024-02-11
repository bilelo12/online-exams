package com.bilelhami.exam.services;

import com.bilelhami.exam.models.ExamAttempt;
import com.bilelhami.exam.models.Invitation;

import java.util.List;
import java.util.Optional;

public interface IInvitationService {

    Invitation create(Invitation invitation);
    List<Invitation> listInvitations();

    List<Invitation> listInvitationsByExamId(long id);

}
