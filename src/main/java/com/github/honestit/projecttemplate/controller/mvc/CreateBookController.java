package com.github.honestit.projecttemplate.controller.mvc;

import com.github.honestit.projecttemplate.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/create-book")
@RequiredArgsConstructor
public class CreateBookController {

    private final BookService bookService;

    @GetMapping
    public String prepareView(Model model) {
        model.addAttribute("createBookForm", new CreateBookForm());
        return "/books/create";
    }

    @PostMapping
    public String processView(@Valid CreateBookForm createBookForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/create";
        }
        bookService.createBook(createBookForm);
        return "redirect:/book-list";
    }
}
