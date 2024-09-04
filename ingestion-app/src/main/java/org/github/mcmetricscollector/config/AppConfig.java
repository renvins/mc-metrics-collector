package org.github.mcmetricscollector.config;

import org.github.mcmetricscollector.security.MetricsBasicAuthFilter;
import org.github.mcmetricscollector.services.UserService;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.configuration.SpringDocUIConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Bean
    SpringDocConfiguration springDocConfiguration(){
        return new SpringDocConfiguration();
    }

    @Bean
    SpringDocConfigProperties springDocConfigProperties() {
        return new SpringDocConfigProperties();
    }

    @Bean
    ObjectMapperProvider objectMapperProvider(SpringDocConfigProperties springDocConfigProperties){
        return new ObjectMapperProvider(springDocConfigProperties);
    }

    @Bean
    SpringDocUIConfiguration SpringDocUIConfiguration(Optional<SwaggerUiConfigProperties> optionalSwaggerUiConfigProperties){
        return new SpringDocUIConfiguration(optionalSwaggerUiConfigProperties);
    }

    @Bean
    FilterRegistrationBean<MetricsBasicAuthFilter> filterRegistrationBean(UserService userService) {
        FilterRegistrationBean<MetricsBasicAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MetricsBasicAuthFilter(userService));
        registrationBean.addUrlPatterns("/v1/manage/*", "/v1/metrics/*");
        return registrationBean;
    }
    
}
