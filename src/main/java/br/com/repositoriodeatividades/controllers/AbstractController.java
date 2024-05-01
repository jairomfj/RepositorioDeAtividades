package br.com.repositoriodeatividades.controllers;

import br.com.repositoriodeatividades.usecases.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

abstract class AbstractController {

    @Autowired
    UserService userService;

    protected User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected Long getCurrentUserID() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userEntity = userService.findByUsername(user.getUsername());

        if (userEntity == null) {
            throw new IllegalStateException("User not found: " + user.getUsername());
        }

        return userEntity.getId();
    }

}
