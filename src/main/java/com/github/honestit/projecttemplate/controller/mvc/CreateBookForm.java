package com.github.honestit.projecttemplate.controller.mvc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class CreateBookForm {

    @NotBlank @Length(min = 3)
    private String title;
    @Builder.Default
    private int pages = 0;
    private AuthorItem mainAuthor;
    @Builder.Default
    private List<AuthorItem> authors = new ArrayList<>();
    @Builder.Default
    private List<CategoryItem> categories = new ArrayList<>();

}
