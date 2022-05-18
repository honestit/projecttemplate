package com.github.honestit.projecttemplate.repository;

import com.github.honestit.projecttemplate.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
