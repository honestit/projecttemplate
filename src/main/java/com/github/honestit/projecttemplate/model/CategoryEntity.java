package com.github.honestit.projecttemplate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @ToString(callSuper = true)
@SuperBuilder
public class CategoryEntity extends BaseEntity {

    @NotBlank
    @Column(unique = true)
    private String name;

    private String description;
}
