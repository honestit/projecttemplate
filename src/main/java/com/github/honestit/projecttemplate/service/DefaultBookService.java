package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.controller.mvc.AuthorItem;
import com.github.honestit.projecttemplate.controller.mvc.CategoryItem;
import com.github.honestit.projecttemplate.controller.mvc.CreateBookForm;
import com.github.honestit.projecttemplate.model.AuthorEntity;
import com.github.honestit.projecttemplate.model.BookEntity;
import com.github.honestit.projecttemplate.repository.AuthorRepository;
import com.github.honestit.projecttemplate.repository.BookRepository;
import com.github.honestit.projecttemplate.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Slf4j
public class DefaultBookService implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<AuthorItem> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(entity -> AuthorItem.builder()
                        .id(entity.getId())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryItem> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(entity -> CategoryItem.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createBook(CreateBookForm createBookForm) {
        log.debug("Creating book base on: {}", createBookForm);
        bookRepository.save(BookEntity.builder()
                .title(createBookForm.getTitle())
                .pages(createBookForm.getPages())
                .mainAuthor(getOrCreateAuthor(createBookForm.getMainAuthor()))
                .authors(createBookForm.getAuthors().stream()
                        .map(this::getOrCreateAuthor)
                        .collect(Collectors.toList()))
                .categories(createBookForm.getCategories().stream()
                        .map(CategoryItem::getId)
                        .map(categoryRepository::getById)
                        .collect(Collectors.toList()))
                .build());
    }

    private AuthorEntity getOrCreateAuthor(AuthorItem authorItem) {
        if (authorItem.getId() != null) {
            return authorRepository.getById(authorItem.getId());
        }
        return authorRepository.findByFirstNameAndLastName(authorItem.getFirstName(), authorItem.getLastName())
                .orElseGet(() -> authorRepository.save(AuthorEntity.builder()
                        .firstName(authorItem.getFirstName())
                        .lastName(authorItem.getLastName())
                        .build()));
    }
}
