package br.com.repositoriodeatividades.usecases.user;

import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.usecases.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindLoggedUser {

    @Autowired
    UserRepositoryInterface userRepository;

    public User find(org.springframework.security.core.userdetails.User currentUser) throws UserNotFoundException {
        User user = userRepository.findByUsername(currentUser.getUsername());
        if(user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

}
