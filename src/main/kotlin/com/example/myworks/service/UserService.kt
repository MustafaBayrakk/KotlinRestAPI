package com.example.myworks.service

import com.example.myworks.domain.model.User
import com.example.myworks.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User? =  userRepository.findById(id).orElse(null)

    fun createUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, user: User): User? {
        with(user){
            val existingUser = userRepository.findById(id).orElse(null)
            return existingUser?.let {
                it.name = name
                it.email = email
                userRepository.save(it)
            }
        }
    }

    fun deleteUser(id: Long): Boolean {
        val existingUser = userRepository.findById(id).orElse(null)
        if (existingUser != null) {
            userRepository.delete(existingUser)
            return true
        }
        return false
    }

    fun getUsersByName(name: String): List<User> = userRepository.findUsersByName(name)
}
