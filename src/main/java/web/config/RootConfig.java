package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
//@ComponentScan("web")
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }

}
