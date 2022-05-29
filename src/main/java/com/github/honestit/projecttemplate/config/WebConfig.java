package com.github.honestit.projecttemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Wybór języka zostanie zapisany przy użyciu ciasteczka (przechowywany po stronie klienta)
    // HINT: Nasz bean musi przysłonić bean typu LocalContextResolver o nazwie "localeResolver",
    // inaczej Spring Boot będzie dalej używał swojego.
    @Bean(name = "localeResolver")
    public LocaleContextResolver localeContextResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.forLanguageTag("pl"));
        return cookieLocaleResolver;
    }

    // Tworzymy beana, który będzie zmieniał wybór języka na podstawie parametru "lang"
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    // Rejestrujemy naszego bean zmieniającego język
    // HINT: Interceptor to mechanizm, który dziala jak filtr, ale dla kontrolerów, a nie dla serwletów.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
