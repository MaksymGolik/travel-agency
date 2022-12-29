package com.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("com.application")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public WebMvcConfig(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Access Bootstrap static resource:
        registry.addResourceHandler("/jquery/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.6.0/");

        registry.addResourceHandler("/popper/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/2.9.3/umd/");

        registry.addResourceHandler("/bootstrap/**") //
                .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/5.1.3/");

        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
