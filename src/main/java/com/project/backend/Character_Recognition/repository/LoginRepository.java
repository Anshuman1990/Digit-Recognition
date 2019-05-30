package com.project.backend.Character_Recognition.repository;

import com.project.backend.Character_Recognition.entity.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends CrudRepository<Login, Long> {
    Login fetchByUserDetails(@Param("email") String email, @Param("password") String password);
}
