package com.github.honestit.projecttemplate.repository;

import com.github.honestit.projecttemplate.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByUser_username(String username);
}
