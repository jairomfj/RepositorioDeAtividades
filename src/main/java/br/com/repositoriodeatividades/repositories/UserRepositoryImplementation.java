package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplementation extends GenericRepositoryImplementation<User> implements UserRepositoryInterface {

    public UserRepositoryImplementation() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
