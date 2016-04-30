package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.User;

public interface UserRepositoryInterface extends GenericRepositoryInterface<User> {

    User findByUsername(String username);

}
