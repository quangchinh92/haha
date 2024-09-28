package chinhtran.JWTServerApp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceCustom {
    @Bean
    public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource =
          new ReloadableResourceBundleMessageSource();
      messageSource.setBasename("classpath:messages");
      messageSource.setDefaultEncoding("UTF-8");
      messageSource.setCacheSeconds(10); // reload messages every 10 seconds
      return messageSource;
    }
}
