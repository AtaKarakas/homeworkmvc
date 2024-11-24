package com.homework_mvc.homeworkmvc.repo;

import com.homework_mvc.homeworkmvc.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}