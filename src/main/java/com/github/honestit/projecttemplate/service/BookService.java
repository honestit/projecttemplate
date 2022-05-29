package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.controller.mvc.AuthorItem;
import com.github.honestit.projecttemplate.controller.mvc.CategoryItem;
import com.github.honestit.projecttemplate.controller.mvc.CreateBookForm;

import java.util.List;

public interface BookService {

    void createBook(CreateBookForm createBookForm);

    List<AuthorItem> getAllAuthors();

    List<CategoryItem> getAllCategories();
}
