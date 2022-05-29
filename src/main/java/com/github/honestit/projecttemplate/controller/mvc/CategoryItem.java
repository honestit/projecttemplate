package com.github.honestit.projecttemplate.controller.mvc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryItem {

    private Long id;
    private String name;
    private String description;

    public static CategoryItem valueOf(String id) {
        return CategoryItem.builder().id(Long.parseLong(id)).build();
    }
}
