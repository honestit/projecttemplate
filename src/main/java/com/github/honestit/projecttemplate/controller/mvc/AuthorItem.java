package com.github.honestit.projecttemplate.controller.mvc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorItem {

    private Long id;
    private String firstName;
    private String lastName;

    public String getFullName() {
        return String.join(" ", firstName, lastName);
    }
}
