package br.com.repositoriodeatividades.usecases.user;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.usecases.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindLoggedUser {

    @Autowired
    UserService userService;

    public UserEntity find(org.springframework.security.core.userdetails.User currentUser) throws UserNotFoundException {
        UserEntity user = userService.findByUsername(currentUser.getUsername());
        if(user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

}
