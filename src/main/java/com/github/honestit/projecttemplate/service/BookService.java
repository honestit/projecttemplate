package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.controller.mvc.BookBaseData;
import com.github.honestit.projecttemplate.controller.mvc.CreateBookForm;

import java.util.List;

public interface BookService {

    void createBook(CreateBookForm createBookForm);

    List<BookBaseData> getAllUserBooks();
}
