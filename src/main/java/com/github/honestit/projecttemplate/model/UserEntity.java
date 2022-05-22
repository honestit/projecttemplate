package com.github.honestit.projecttemplate.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(exclude = "password") @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

//    Wariant kiedy jeden użytkownik może mieć więcej niż jedną rolę.
//    @ElementCollection
//    @CollectionTable(name = "users_roles")
//    private List<String> roles;
}
