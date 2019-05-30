package com.project.backend.Character_Recognition.repository;

import com.project.backend.Character_Recognition.entity.Register;
import org.springframework.data.repository.CrudRepository;

public interface RegisterRepository extends CrudRepository<Register, Long> {
}
