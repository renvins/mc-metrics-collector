package org.github.mcmetricscollector.config;

import org.github.mcmetricscollector.common.config.RedisConfig;
import org.github.mcmetricscollector.common.service.RedisService;
import org.github.mcmetricscollector.common.service.impl.RedisServiceImpl;
import org.github.mcmetricscollector.security.MetricsBasicAuthFilter;
import org.github.mcmetricscollector.security.ThreadLocalRemover;
import org.github.mcmetricscollector.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.configuration.SpringDocUIConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
public class AppConfig {

    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    RedisConfig redisConfig() {
        String host = environment.getProperty("redis.host");
        int port = Integer.parseInt(environment.getProperty("redis.port"));

        String username = environment.getProperty("redis.username");
        String password = environment.getProperty("redis.password");

        return new RedisConfig(host, port, username, password);
    }

    @Bean
    RedisService redisService(RedisConfig redisConfig) {
        return new RedisServiceImpl(redisConfig);
    }

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

    @Bean
    WebMvcConfigurer webMvcConfigurer(ThreadLocalRemover threadLocalRemover) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NotNull InterceptorRegistry registry) {
                registry.addInterceptor(threadLocalRemover);
            }
        };
    }
    
}
