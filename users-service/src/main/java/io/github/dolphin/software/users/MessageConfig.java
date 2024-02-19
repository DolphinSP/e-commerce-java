package io.github.dolphin.software.users;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is responsible for configuring and providing the message source for localization in a web application.
 */
@Configuration
public class MessageConfig implements WebMvcConfigurer {

    /**
     * Creates a LocalValidatorFactoryBean that is responsible for validating objects using a message source for localization.
     *
     * @param messageSource the message source used for localization
     * @return a LocalValidatorFactoryBean configured with the provided message source
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    /**
     * Creates and configures a message source for localization in a web application.
     *
     * @return the configured {@link MessageSource} object
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("languages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
