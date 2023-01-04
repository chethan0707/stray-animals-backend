package com.koderkt.stray_animalsbackend.users.repositories

import com.koderkt.stray_animalsbackend.users.models.UserReports
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserReportsRepository : MongoRepository<UserReports, String> {

    fun findByngoId(email: String): List<UserReports>
    fun findAllByuserId(userId:String):List<UserReports>
}