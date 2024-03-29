package br.com.repositoriodeatividades.entities;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity(name = "UserRole")
@Table(name = "user_roles")
public class UserRoleEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    private UserEntity user;

    @NotNull
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
