package com.example.myworks.controller

import com.example.myworks.domain.model.User
import com.example.myworks.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping(headers = ["Authorization=Bearer token123"])
    fun getAllUsers1(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        println("kullanıcılar çekildi")
        return ResponseEntity(users, HttpStatus.OK)
    }

    @RequestMapping("/header", method = [RequestMethod.GET], headers = ["Authorization"])
    fun getAllUsers2(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        println("kullanıcılar çekildi")
        return ResponseEntity(users, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(required = true) id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return if (user != null) {
            println("kullanıcı bulundu")
            ResponseEntity(user, HttpStatus.OK)
        } else {
            println("kullanıcı bulunamadı")
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/getById")
    fun getUserByParamId(@RequestParam("id", required = false, defaultValue = "1") id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return if (user != null) {
            println("kullanıcı bulundu")
            ResponseEntity(user, HttpStatus.OK)
        } else {
            println("kullanıcı bulunamadı")
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/create", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
        println("kullanıcı yaratıldı : $createdUser")
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, user)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        val deleted = userService.deleteUser(id)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/getByName")
    fun getUserByName(@RequestParam("name") name: String): ResponseEntity<List<User>> {
        val user = userService.getUsersByName(name)
        return if (user.isNotEmpty()) {
            println("kullanıcı bulundu")
            ResponseEntity(user, HttpStatus.OK)
        } else {
            println("kullanıcı bulunamadı")
            ResponseEntity.notFound().build()
        }
    }
}
