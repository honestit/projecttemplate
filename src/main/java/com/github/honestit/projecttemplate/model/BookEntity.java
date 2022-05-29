package com.github.honestit.projecttemplate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString(exclude = {"authors"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookEntity extends BaseEntity {

    @NotBlank
    private String title;

    @PositiveOrZero
    private int pages;

    @ManyToOne
    private AuthorEntity mainAuthor;

    @ManyToMany
    private List<AuthorEntity> authors;
}
