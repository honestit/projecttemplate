package com.github.honestit.projecttemplate.controller.mvc;

import com.github.honestit.projecttemplate.repository.UserRepository;
import com.github.honestit.projecttemplate.service.BookService;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("Create book controller specification")
// Adnotacja włącza wszystko, co potrzebne do przetestowania konkretnego kontrolera
@WebMvcTest(value = CreateBookController.class)
class CreateBookControllerTest {

    // Mockujemy zależności naszego kontrolera
    @MockBean
    private BookService bookService;

    // Potrzebne aby zamokować zależności do naszego AuthService, który jest tworzony w SecurityConfig
    // a SecurityConfig (jako klasa związana ze Spring Security) jest automatycznie importowany
    // przez @WebMvcTest
    @MockBean
    private UserRepository userRepository;

    // Klasa służąca do testowania Spring MVC (kontrolerów)
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should prepare a view for creating book")
    public void prepareView_success() throws Exception {
        mockMvc.perform(
                        // Ta klasa służy do budowania żądań, które symulujemy
                        MockMvcRequestBuilders.get("/create-book"))
                // Oczekujemy, że STATUS jest OK, czyli 200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Sprawdzamy, czy otrzymaliśmy poprawny widok
                .andExpect(MockMvcResultMatchers.view().name("/books/create"))
                // Sprawdzamy, czy w modelu jest to, czego oczekujemy
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "authors"))
                .andExpect(MockMvcResultMatchers.model().attribute("createBookForm", new CreateBookForm()))
                // Używamy, aby w logach testów pojawiła się pełna informacja ze Spring MVC
                // np. co jest w modelu, jaki jest stan walidacji itd.
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Should create a new book")
    public void processView_success() throws Exception {
        ArgumentCaptor<CreateBookForm> captor = ArgumentCaptor.forClass(CreateBookForm.class);
        Mockito.doNothing().when(bookService).createBook(captor.capture());

        mockMvc.perform(MockMvcRequestBuilders.post("/create-book")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("createBook", "")
                        .param("title", "test title"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/book-list"))
                .andDo(MockMvcResultHandlers.print());

        // DO SPRAWDZENIA!
//        // Tworzymy obiekt "przechwytujący" konkretną klasę
//        ArgumentCaptor<CreateBookForm> captor = ArgumentCaptor.forClass(CreateBookForm.class);
//        // Sprawdzamy czy serwis został użyty dokładnie raz
//        // + tworzymy obiekt do przechwycenia
//        Mockito.verify(bookService, Mockito.atLeast(1)).createBook(captor.capture());
        // Pobieramy faktyczną wartość, która została użyta.
        CreateBookForm createdBookForm = captor.getValue();
        Truth.assertThat(createdBookForm).isEqualTo(CreateBookForm.builder()
                .title("test title")
                .build());
    }

}