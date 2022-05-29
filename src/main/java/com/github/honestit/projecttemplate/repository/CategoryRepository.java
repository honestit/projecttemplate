package com.github.honestit.projecttemplate.repository;

import com.github.honestit.projecttemplate.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
