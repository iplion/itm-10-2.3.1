package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import web.entity.User;
import web.service.UserService;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userService.findAll().isEmpty()) {
            userService.save(new User("Gogi", null, "black"));
            userService.save(new User("Vasilek", "Romashechka", "red"));
            userService.save(new User("", "null", "blue"));
            userService.save(new User(null, "HeroZavr", "yellow"));
            userService.save(new User("Gavrilla", null, "purple"));
        }
    }
}
