package br.com.repositoriodeatividades.configs;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.entities.UserRoleEntity;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.UserRoleRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    UserRepositoryInterface userRepository;

    @Autowired
    UserRoleRepositoryInterface userRoleRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            var userOptional = userRepository.findByUsername("admin");
            if (userOptional.isEmpty()) {
                createUser();
            }
        } catch (Exception e) {
            log.warn("Could not find user");
            createUser();
        }

    }

    private void createUser() {

        try {
            UserEntity newUser = new UserEntity();
            newUser.setUsername("admin");
            newUser.setPassword("admin");
            newUser.setEnabled(true);
            userRepository.save(newUser);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUser(newUser);
            userRole.setRole("ADMIN_ROLE");
            userRoleRepository.save(userRole);

        } catch (Exception e) {
            log.warn("Could not create user");
        }
    }

}
