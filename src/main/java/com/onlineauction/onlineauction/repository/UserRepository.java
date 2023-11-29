package com.onlineauction.onlineauction.repository;

import com.onlineauction.onlineauction.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // custom queries if needed
}

