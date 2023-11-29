package com.onlineauction.onlineauction.service;
import com.onlineauction.onlineauction.exception.CustomIntegrityConstraintViolationException;
import com.onlineauction.onlineauction.exception.ResourceNotFoundException;
import com.onlineauction.onlineauction.model.Bid;
import com.onlineauction.onlineauction.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import com.onlineauction.onlineauction.model.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class
UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User createUser(@Valid User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new CustomIntegrityConstraintViolationException("Integrity constraint violation: User name should be unique");
            } else {
                throw ex;
            }
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}