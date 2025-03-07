package br.com.ifba.irebus.config;

import jakarta.validation.constraints.Email;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //permite todas as rotas
                .allowedOrigins("http://127.0.0.1:5500/pages/cadastro.html")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //metodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
