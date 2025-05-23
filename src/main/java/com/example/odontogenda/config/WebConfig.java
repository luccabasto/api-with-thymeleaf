package com.example.odontogenda.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.*;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 1) Onde ficam os arquivos messages_*.properties
    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:messages");
        ms.setDefaultEncoding("UTF-8");
        ms.setFallbackToSystemLocale(false);
        return ms;
    }

    // 2) Resolver de locale usando cookie
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new CookieLocaleResolver();
        slr.setDefaultLocale(new Locale("pt"));
        slr.setCookieName("LANG");
        slr.setCookieMaxAge(3600);
        return slr;
    }

    // 3) Interceptor para trocar idioma via parâmetro "?lang=xx"
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        registry.addInterceptor(lci);
    }

    // 4) Permitir acesso a recursos estáticos (css/js) normalmente
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/css/**", "/js/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/js/");
    }
}
