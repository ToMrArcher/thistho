package com.eidjord.enterpriseexam.controller

import com.eidjord.enterpriseexam.model.AuthorityEntity
import com.eidjord.enterpriseexam.model.UserEntity
import com.eidjord.enterpriseexam.service.UserService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api")
class AuthController(@Autowired private val userService: UserService) {

    @GetMapping("/authority/all")
    fun getAuthorities(): ResponseEntity<List<AuthorityEntity>>{

        return ResponseEntity.ok().body(userService.getAuthorities())
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody newUserInfo: NewUserInfo): ResponseEntity<UserEntity>{
        val createdUser = userService.registerUser(newUserInfo)
        val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString())
        return ResponseEntity.created(uri).body(createdUser)
    }

}

data class NewUserInfo(val email: String, val password: String)
