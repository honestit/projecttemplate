package com.github.honestit.projecttemplate.service;

import com.github.honestit.projecttemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

// Nie ma adnotacji @Service, bo jest już wystawiony jako bean
// w klasie SecurityConfig
public class AuthService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                // Użytkownika z bazy (naszego) zamieniamy na użytkownika Spring Security
                .map(userEntity -> new User(
                        // podając nazwę użytkownika
                        userEntity.getUsername(),
                        // podając hasło
                        userEntity.getPassword(),
                        // podając listę jego ról
                        // u nas w podstawowym wariancie jest 1 rola
                        List.of(new SimpleGrantedAuthority(userEntity.getRole()))))
                        // jeżeli nasza aplikacja nie obsługuje ról, to podajemy
                        // jakąś sztuczną rolę, bo musi być
//                        List.of(new SimpleGrantedAuthority("USER"))))
                .orElseThrow(() -> new UsernameNotFoundException("No user with username " + username));
    }

    // Wykorzystanie wstrzykiwania przez setter pomaga w problemach z kolejnością zależności database<->security
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
