package com.github.honestit.projecttemplate.controller.mvc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class BookBaseData {

    private Long id;
    private String title;
    private String author;
}
