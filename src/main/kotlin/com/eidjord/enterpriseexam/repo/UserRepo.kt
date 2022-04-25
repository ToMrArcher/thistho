package com.eidjord.enterpriseexam.repo

import com.eidjord.enterpriseexam.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String): UserEntity?

}