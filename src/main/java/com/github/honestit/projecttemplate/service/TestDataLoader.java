package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.model.AuthorEntity;
import com.github.honestit.projecttemplate.model.CategoryEntity;
import com.github.honestit.projecttemplate.model.UserEntity;
import com.github.honestit.projecttemplate.repository.AuthorRepository;
import com.github.honestit.projecttemplate.repository.CategoryRepository;
import com.github.honestit.projecttemplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create-drop")
@Component
@Slf4j
@RequiredArgsConstructor
public class TestDataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @EventListener
    @Transactional
    public void loadData(ContextRefreshedEvent event) {
        log.debug("Loading data ...");
        userRepository.save(UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("pass"))
                        .role("ADMIN")
                .build());
        authorRepository.save(AuthorEntity.builder()
                        .firstName("John")
                        .lastName("McDonald")
                .build());
        authorRepository.save(AuthorEntity.builder()
                        .firstName("Aleksander")
                        .lastName("Fredro")
                .build());
        categoryRepository.save(CategoryEntity.builder()
                        .name("Fantasy")
                .build());
        categoryRepository.save(CategoryEntity.builder()
                        .name("Drama")
                .build());
        log.debug("Data saved");
    }
}
