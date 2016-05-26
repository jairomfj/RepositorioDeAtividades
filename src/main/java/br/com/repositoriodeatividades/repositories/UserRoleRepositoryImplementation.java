package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.UserRole;
import br.com.repositoriodeatividades.repositories.interfaces.UserRoleRepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepositoryImplementation extends GenericRepositoryImplementation<UserRole> implements UserRoleRepositoryInterface {

    public UserRoleRepositoryImplementation() {
        super(UserRole.class);
    }

}
