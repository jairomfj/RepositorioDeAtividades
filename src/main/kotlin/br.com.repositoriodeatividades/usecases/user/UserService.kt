package br.com.repositoriodeatividades.usecases.user

import br.com.repositoriodeatividades.entities.UserEntity
import br.com.repositoriodeatividades.entities.UserRoleEntity
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface
import br.com.repositoriodeatividades.repositories.interfaces.UserRoleRepositoryInterface
import org.springframework.stereotype.Service
import java.time.LocalDateTime


enum class UserRole {
    ADMIN,
}

data class CreateUserInput(
        val username: String,
        val password: String,
        val role: UserRole,
)

@Service
class UserService(
        private val userRepository: UserRepositoryInterface,
        private val userRoleRepository: UserRoleRepositoryInterface,
    ) {

    fun findByUsername(username: String): UserEntity? {
        return userRepository.findByUsername(username)
    }

    fun createUser(input: CreateUserInput): Boolean {
        try {
            val password = input.password

            val newUser = UserEntity()
            newUser.username = input.username
            newUser.password = password
            newUser.isEnabled = true
            newUser.createdAt = LocalDateTime.now()

            userRepository.save(newUser)

            val role = when(input.role) {
                UserRole.ADMIN -> "ADMIN_ROLE"
            }


            val userRole = UserRoleEntity()
            userRole.user = newUser
            userRole.role = role
            userRoleRepository.save(userRole)

            return true
        } catch (e: Exception) {
            return false
        }
    }
}