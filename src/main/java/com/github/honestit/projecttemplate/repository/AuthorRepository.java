package com.github.honestit.projecttemplate.repository;

import com.github.honestit.projecttemplate.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
