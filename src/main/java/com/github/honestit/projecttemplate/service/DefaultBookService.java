package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.controller.mvc.CreateBookForm;
import com.github.honestit.projecttemplate.model.AuthorEntity;
import com.github.honestit.projecttemplate.model.BookEntity;
import com.github.honestit.projecttemplate.repository.AuthorRepository;
import com.github.honestit.projecttemplate.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void createBook(CreateBookForm createBookForm) {
        AuthorEntity mainAuthor;
        if (createBookForm.getAuthorId() != null) {
            mainAuthor = authorRepository.getById(createBookForm.getAuthorId());
        } else {
            mainAuthor = authorRepository
                    .findByFirstNameAndLastName(createBookForm.getAuthorFirstName(),
                            createBookForm.getAuthorLastName())
                    .orElseGet(() -> authorRepository.save(AuthorEntity.builder()
                            .firstName(createBookForm.getAuthorFirstName())
                            .lastName(createBookForm.getAuthorLastName())
                            .build()));
        }
        bookRepository.save(BookEntity.builder()
                .title(createBookForm.getTitle())
                .pages(createBookForm.getPages())
                .mainAuthor(mainAuthor)
                .build());
    }
}
