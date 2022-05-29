package com.github.honestit.projecttemplate.controller.mvc;

import com.github.honestit.projecttemplate.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/create-book")
@RequiredArgsConstructor
public class CreateBookController {

    private final BookService bookService;

    @ModelAttribute("categories")
    public List<CategoryItem> categories() {
        return bookService.getAllCategories();
    }

    @ModelAttribute("authors")
    public List<AuthorItem> authors() {
        return bookService.getAllAuthors();
    }

    @GetMapping
    public String prepareView(Model model) {
        model.addAttribute("createBookForm", new CreateBookForm());
        return "/books/create";
    }

    @PostMapping(params = "createBook")
    public String createBook(@Valid CreateBookForm createBookForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/create";
        }
        bookService.createBook(createBookForm);
        return "redirect:/book-list";
    }

    @PostMapping(params = "addAuthor")
    public String addAuthor(@ModelAttribute CreateBookForm createBookForm, AuthorItem authorItem) {
        createBookForm.getAuthors().add(authorItem);
        return "/books/create";
    }

    @PostMapping(params = "removeAuthor")
    public String removeAuthor(@ModelAttribute CreateBookForm createBookForm, @RequestParam int removeAuthor) {
        createBookForm.getAuthors().remove(removeAuthor);
        return "/books/create";
    }
}
