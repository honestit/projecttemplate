package com.github.honestit.projecttemplate.repository;

import com.github.honestit.projecttemplate.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
