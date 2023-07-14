package com.example.myworks.repository

import com.example.myworks.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name") //JPQL (Java Persistence Query Language)
    fun findUsersByName(@Param("name") name: String): List<User>
}
