package com.onlineauction.onlineauction.controller;

import com.onlineauction.onlineauction.model.Auction;
import com.onlineauction.onlineauction.model.User;
import com.onlineauction.onlineauction.response.BidResponse;
import com.onlineauction.onlineauction.response.UserResponse;
import com.onlineauction.onlineauction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
        logger.info("Request received: Creating User");
        User createdUser = userService.createUser(user);
        logger.info("User Created Successfully");
        return UserResponse.createUserBuilder(HttpStatus.CREATED,createdUser);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        logger.info("Request received: Getting Bid By AuctionId");
        List<User> users = userService.getAllUsers();
        logger.info("User details received Successfully");
        return UserResponse.getAllUsersBuilder(HttpStatus.OK,users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        logger.info("Request received: Getting User By Id");
        User user = userService.getUserById(id);
        logger.info("User details received Successfully");
        return UserResponse.getUserBuilder(HttpStatus.OK,user);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        logger.info("Request received: Deleting User");
        User user = userService.getUserById(userId);
        userService.deleteUser(userId);
        logger.info("User Deleted Successfully");
        return UserResponse.deleteUserBuilder(HttpStatus.NO_CONTENT);
    }

}
