package br.com.repositoriodeatividades.configs;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.entities.UserRoleEntity;
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
        var userOptional = userRepository.findByUsername("admin");
        if(userOptional.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setUsername("admin");
            newUser.setPassword("admin");
            userRepository.save(newUser);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUser(newUser);
            userRole.setRole("ADMIN_ROLE");
            userRoleRepository.save(userRole);

        }
    }

}
