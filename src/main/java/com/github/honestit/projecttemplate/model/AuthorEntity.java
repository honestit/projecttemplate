package com.github.honestit.projecttemplate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "authors")
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class AuthorEntity extends BaseEntity{

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String nickname;
}
