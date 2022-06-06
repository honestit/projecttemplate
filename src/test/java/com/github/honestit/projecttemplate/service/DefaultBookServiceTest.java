package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.controller.mvc.AuthorItem;
import com.github.honestit.projecttemplate.model.AuthorEntity;
import com.github.honestit.projecttemplate.repository.AuthorRepository;
import com.github.honestit.projecttemplate.repository.BookRepository;
import com.github.honestit.projecttemplate.repository.CategoryRepository;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DisplayName("Default book service specification")
// Integracja Spring z JUnit5
@ExtendWith(SpringExtension.class)
// Importujemy te klasy (komponenty, konfiguracje), których rzeczywistego kodu
// chcemy używać w teście
@Import(DefaultBookService.class)
class DefaultBookServiceTest {

    // "Mockujemy" zależności naszego serwisu, aby nie były potrzebne faktyczne obiekt
    // Adnotacja ta jednocześnie rejestruje nasze pola jako beany Springa,
    // potem możemy "nagrać" ich zachowanie (powiedzieć, która metoda robi co i kiedy).
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private DefaultBookService classUnderTest;

    @Test
    @DisplayName("Should return all authors as author items")
    public void getAllAuthors_success() {
        // Arrange
        Mockito.when(authorRepository.findAll()).thenReturn(
                List.of(
                        AuthorEntity.builder()
                                .id(1L)
                                .firstName("firstName")
                                .lastName("lastName")
                                .build()));

        // Act
        List<AuthorItem> results = classUnderTest.getAllAuthors();

        // Assert
//        Truth.assertThat(results).hasSize(1); // Druga asercja sprawdza to samo!
        Truth.assertThat(results).containsExactly(AuthorItem.builder()
                .id(1L).firstName("firstName").lastName("lastName").build());
    }

}