package com.eidjord.enterpriseexam.service

import com.eidjord.enterpriseexam.controller.NewUserInfo
import com.eidjord.enterpriseexam.model.AuthorityEntity
import com.eidjord.enterpriseexam.model.UserEntity
import com.eidjord.enterpriseexam.repo.AuthorityRepo
import com.eidjord.enterpriseexam.repo.UserRepo
import org.apache.commons.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.client.HttpClientErrorException.Forbidden
import org.springframework.web.client.HttpClientErrorException.NotFound
import sun.java2d.pipe.SpanShapeRenderer.Simple

@Service
class UserService(@Autowired private val userRepo: UserRepo, @Autowired private val authorityRepo: AuthorityRepo) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        username?.let {
            val user = userRepo.findByEmail(username)
            return User(user?.email, user?.password, user?.authorities?.map { authorityEntity -> SimpleGrantedAuthority(authorityEntity.authorityName) })
        }
        throw java.lang.Exception("Invalid user info")
    }

    fun getAuthorities(): List<AuthorityEntity>{
        return authorityRepo.findAll()
    }

    fun registerUser(newUserInfo: NewUserInfo): UserEntity{
        val newUser = UserEntity(email = newUserInfo.email, password = BCryptPasswordEncoder().encode(newUserInfo.password))
        val authority: AuthorityEntity? = getAuthority("USER")
        authority?.let{
            newUser.authorities?.add(authority)
        }
        return userRepo.save(newUser)
    }

    fun getAuthority(name: String): AuthorityEntity?{
        return authorityRepo.getByAuthorityName(name)
    }

}