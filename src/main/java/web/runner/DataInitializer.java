package web.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web.entity.User;
import web.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Initializing Data...");
        if (userService.findAll().isEmpty()) {
            userService.save(new User("Gogi", null, "black"));
            userService.save(new User("Vasilek", "Romashechka", "red"));
            userService.save(new User("", "null", "blue"));
            userService.save(new User(null, "HeroZavr", "yellow"));
            userService.save(new User("Gavrilla", null, "purple"));
        }
    }
}
