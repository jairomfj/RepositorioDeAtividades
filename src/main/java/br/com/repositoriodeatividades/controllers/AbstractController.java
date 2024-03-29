package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

abstract class AbstractController {

    @Autowired
    UserRepositoryInterface userRepository;

    protected User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected Long getCurrentUserID() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userOptional = userRepository.findByUsername(user.getUsername());
        var userEntity = userOptional.orElseThrow();

        return userEntity.getId();
    }

}
