package com.homework_mvc.homeworkmvc.repo;

import com.homework_mvc.homeworkmvc.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "userRepo")
public interface UserRepository extends JpaRepository<UserModel,Long> {

    UserModel findByEmail(String email);
    UserModel findByUsername(String userName);
    UserModel findFirstByUsername(String username);
}
