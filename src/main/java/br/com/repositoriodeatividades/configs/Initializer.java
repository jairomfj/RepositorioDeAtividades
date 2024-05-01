package br.com.repositoriodeatividades.configs;

import br.com.repositoriodeatividades.usecases.user.CreateUserInput;
import br.com.repositoriodeatividades.usecases.user.UserRole;
import br.com.repositoriodeatividades.usecases.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Override
    public void afterPropertiesSet() {
        try {
            var userOptional = userService.findByUsername("admin");
            if (userOptional == null) {
                createAdminUser();
            }
        } catch (Exception e) {
            log.warn("Could not find user");
            createAdminUser();
        }
    }

    private void createAdminUser() {
        userService.createUser(new CreateUserInput(
                "admin",
                "admin@433@teste",
                UserRole.ADMIN)
        );
    }

}
