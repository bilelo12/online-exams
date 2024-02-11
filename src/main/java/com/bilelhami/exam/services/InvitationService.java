package com.bilelhami.exam.services;

import com.bilelhami.exam.models.Invitation;
import com.bilelhami.exam.repositories.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService implements IInvitationService {
    private final InvitationRepository invitationRepository ;

    @Override
    public Invitation create(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    @Override
    public List<Invitation> listInvitations() {
        return invitationRepository.findAll();
    }

    @Override
    public List<Invitation> listInvitationsByExamId(long id) {
        return invitationRepository.findAllByExam_Id(id);
    }
}
