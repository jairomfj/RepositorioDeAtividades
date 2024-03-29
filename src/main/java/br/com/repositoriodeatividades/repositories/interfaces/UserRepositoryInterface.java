package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.UserEntity;

import java.util.Optional;

public interface UserRepositoryInterface extends GenericRepositoryInterface<UserEntity> {

    Optional<UserEntity> findByUsername(String username);

}
