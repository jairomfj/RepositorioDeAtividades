package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.UserEntity;

public interface UserRepositoryInterface extends GenericRepositoryInterface<UserEntity> {

    UserEntity findByUsername(String username);

}
