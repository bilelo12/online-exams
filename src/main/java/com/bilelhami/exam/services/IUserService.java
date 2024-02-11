package com.bilelhami.exam.services;

import com.bilelhami.exam.models.Question;
import com.bilelhami.exam.models.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User create(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Boolean userExists(String email);


    User update(Long id , User user) throws ChangeSetPersister.NotFoundException;
    void delete(Long id);

}
