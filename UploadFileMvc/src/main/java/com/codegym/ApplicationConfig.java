package com.codegym;

import com.codegym.persistence.GeneralPersistence;
import com.codegym.persistence.ProductPersistenceImpl;
import com.codegym.service.GeneralService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan("com.codegym.controller")
@PropertySource("classpath:global_config_app.properties")
public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    @Autowired
    Environment env;
    private ApplicationContext applicationContext;

    // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String fileUpload = env.getProperty("file_upload").toString();
        // Image resource.
        registry.addResourceHandler("/images/**") //
                .addResourceLocations("file:" + fileUpload);

    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



    //Thymeleaf Configuration
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() throws IOException
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20848820);
        return multipartResolver;
    }

  @Bean
    public ProductPersistenceImpl productPersistence(){
        return new ProductPersistenceImpl();
  }
}
