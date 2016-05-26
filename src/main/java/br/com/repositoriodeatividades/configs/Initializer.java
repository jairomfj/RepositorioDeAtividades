package br.com.repositoriodeatividades.configs;

import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.entities.UserRole;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.UserRoleRepositoryInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements InitializingBean {

    @Autowired
    UserRepositoryInterface userRepository;

    @Autowired
    UserRoleRepositoryInterface userRoleRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        createUser();
    }


    private void createUser() {
        User user = userRepository.findByUsername("user");
        if(user == null) {
            User newUser = new User();
            user.setUsername("user");
            user.setPassword("pass");
            userRepository.save(newUser);

            UserRole userRole = new UserRole();
            userRole.setUser(newUser);
            userRole.setRole("USER_ROLE");
            userRoleRepository.save(userRole);

        }
    }

}
