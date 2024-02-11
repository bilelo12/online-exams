package com.bilelhami.exam.services;

import com.bilelhami.exam.models.User;
import com.bilelhami.exam.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{

    private  final UserRepository userRepository ;
    @Override
    public User create(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id)
    {

        return userRepository.findById(id);
    }

    @Override
    public Boolean userExists(String email)
    {
        Optional<User> theUser = userRepository.findByEmail(email);
        return  theUser.isPresent() ;
    }

    @Override
    public User update(Long id, User user) throws ChangeSetPersister.NotFoundException {
        Optional<User> theUser = userRepository.findById(id);
        if(theUser.isPresent()){
            User userToUpdate = theUser.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());

            userToUpdate.setEnabled(user.isEnabled());
            userToUpdate.setRole(user.getRole());
            userToUpdate.setPassword(user.getPassword());
            return userRepository.save(userToUpdate);
        }else{
            throw  new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void delete(Long id) {

    }
}
