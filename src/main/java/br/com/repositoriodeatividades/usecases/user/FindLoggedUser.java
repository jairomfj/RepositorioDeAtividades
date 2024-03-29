package br.com.repositoriodeatividades.usecases.user;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.usecases.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindLoggedUser {

    @Autowired
    UserRepositoryInterface userRepository;

    public UserEntity find(org.springframework.security.core.userdetails.User currentUser) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(currentUser.getUsername());
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

}
