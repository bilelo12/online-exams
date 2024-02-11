package com.bilelhami.exam.controllers;


import com.bilelhami.exam.models.Exam;
import com.bilelhami.exam.models.Invitation;
import com.bilelhami.exam.services.EmailService;
import com.bilelhami.exam.services.IExamService;
import com.bilelhami.exam.services.IInvitationService;
import com.bilelhami.exam.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/invitations")
@RequiredArgsConstructor
public class InvitationController {

    private final IInvitationService invitationService ;
    private  final IUserService userService ;
    private final IExamService examService;
    private final EmailService emailService ;
    @GetMapping("")
    public ResponseEntity<List<Invitation>> getAll(){
        List<Invitation> invitations = invitationService.listInvitations();
        return ResponseEntity.ok(invitations);
    }
    @PostMapping("")
    public ResponseEntity<Invitation> createInvitation(@Valid @RequestBody Invitation invitation){
        Invitation createdInvitation = invitationService.create(invitation);
        var exam = examService.getExamById(createdInvitation.getId());
        if (!userService.userExists(invitation.getStudentEmail())) {
            sendInvitationEmail(invitation.getStudentEmail(),createdInvitation.getToken(), exam);
        }
        return ResponseEntity.status(CREATED).body(createdInvitation);
    }



    @GetMapping("/exam/{id}")
    public ResponseEntity<List<Invitation>> getAllByExamId(@PathVariable long id){
        List<Invitation> invitations = invitationService.listInvitationsByExamId(id);
        return ResponseEntity.ok(invitations);
    }

    private void sendInvitationEmail(String studentEmail, String token, Optional<Exam> exam) {
        String examDescription = exam.get().getDescription();
        String examTitle = exam.get().getTitle();
        // Replace with the actual URL
        //******************
        String examLink = "https://your-server.com/exam/join?token=" + token;
        //******************

        String subject = "Invitation to Exam: " + examTitle;
        String body = "<html><body>" +
                "<p>You have been invited to participate in the exam:</p>" +
                "<p><strong>" + examTitle + "</strong></p>" +
                "<p>Description: " + examDescription + "</p>" +
                "<p>Click the button/link below to join the exam:</p>" +
                "<a href=\"" + examLink + "\"><button style=\"background-color: #4CAF50; color: white; padding: 10px 15px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;\">Join Exam</button></a>" +
                "</body></html>";

        emailService.sendHtmlEmail(studentEmail, subject, body);
    }

}
