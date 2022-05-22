package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.controller.mvc.BookBaseData;
import com.github.honestit.projecttemplate.controller.mvc.CreateBookForm;
import com.github.honestit.projecttemplate.model.AuthorEntity;
import com.github.honestit.projecttemplate.model.BookEntity;
import com.github.honestit.projecttemplate.repository.AuthorRepository;
import com.github.honestit.projecttemplate.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<BookBaseData> getAllUserBooks() {
        // Pobieramy nazwę aktualnie zalogowanego użytkownika (aktualnie = związanego z requestem, który przetwarzamy).
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return bookRepository.findAllByUser_username(username).stream()
                .map(bookEntity -> BookBaseData.builder()
                        .id(bookEntity.getId())
                        .title(bookEntity.getTitle())
                        .author(bookEntity.getMainAuthor().getFirstName() +
                                " " + bookEntity.getMainAuthor().getLastName())
                        .build())
                .collect(Collectors.toList());
    }

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
