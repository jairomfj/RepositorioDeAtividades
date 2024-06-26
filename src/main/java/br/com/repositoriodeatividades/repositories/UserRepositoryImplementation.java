package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImplementation extends GenericRepositoryImplementation<UserEntity> implements UserRepositoryInterface {

    public UserRepositoryImplementation() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username = :username", UserEntity.class)
                .setParameter("username", username).getSingleResult();
    }
}
