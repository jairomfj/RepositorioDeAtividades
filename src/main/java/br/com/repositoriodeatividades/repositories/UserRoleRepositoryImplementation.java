package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.UserRoleEntity;
import br.com.repositoriodeatividades.repositories.interfaces.UserRoleRepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepositoryImplementation extends GenericRepositoryImplementation<UserRoleEntity> implements UserRoleRepositoryInterface {

    public UserRoleRepositoryImplementation() {
        super(UserRoleEntity.class);
    }

}
