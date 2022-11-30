package com.koderkt.stray_animalsbackend.users.repositories

import com.koderkt.stray_animalsbackend.users.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findUserByEmail(email:String): Optional<User>
    fun findUserById(id: String?): User
}