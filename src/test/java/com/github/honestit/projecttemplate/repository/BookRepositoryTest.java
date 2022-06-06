package com.github.honestit.projecttemplate.repository;

import com.github.honestit.projecttemplate.model.BookEntity;
import com.github.honestit.projecttemplate.model.CategoryEntity;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

// Wymagane do działania:
// 1. Dodać zaleźność do h2 (lub innej in-memory), bo domyślnie jest wymagana przez @DataJpaTest
// 2. Dodać application.properties dla katalogu "test" (obok katalogu "java") z konfiguracją aplikacji
// pod testy (bez bezpośredniej konfiguracji bazy danych na sql).

@DisplayName("Book repository specification")
// Konfiguruje wszystko, co potrzeba, aby pisać testy jednostkowe dla repozytoriów jpa
@DataJpaTest
class BookRepositoryTest {

    // EntityManager, który nie utrwala zmian (cofa je razem z transakcją), ale pozwala przygotować
    // dane testowe i weryfikować stan bazy danych.
    @Autowired
    private TestEntityManager testEntityManager;

    // Wstrzykujemy repo, które chcemy testować
    @Autowired
    private BookRepository bookRepository; // classUnderTest, cut

    @Test
    @DisplayName("Should find all books with given category")
    public void findAllByCategoriesContains_success() {
        // AAA
        // Arrange
        CategoryEntity testCategory = CategoryEntity.builder().name("Test 1").build();
        testEntityManager.persist(testCategory);
        testEntityManager.persist(BookEntity.builder().title("Test title 1").categories(List.of(testCategory)).build());
        testEntityManager.persist(BookEntity.builder().title("Test title 2").categories(List.of(testCategory)).build());

        // Act
        List<BookEntity> results = bookRepository.findAllByCategoriesContains(testCategory);

        // Assert
        Truth.assertThat(results).hasSize(2);
    }

    @Test
    @DisplayName("Should not find any books for not related category")
    public void findAllByCategoriesContains_emptyList() {
        // AAA
        // Arrange
        CategoryEntity testCategory1 = CategoryEntity.builder().name("Test 1").build();
        CategoryEntity testCategory2 = CategoryEntity.builder().name("Test 2").build();
        testEntityManager.persist(testCategory1);
        testEntityManager.persist(testCategory2);
        testEntityManager.persist(BookEntity.builder().title("Test title 1").categories(List.of(testCategory1)).build());
        testEntityManager.persist(BookEntity.builder().title("Test title 2").categories(List.of(testCategory1)).build());

        // Act
        List<BookEntity> results = bookRepository.findAllByCategoriesContains(testCategory2);

        // Assert
        Truth.assertThat(results).isEmpty();

    }


}