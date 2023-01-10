package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.AdoptionPost
import com.koderkt.stray_animalsbackend.users.models.User
import com.koderkt.stray_animalsbackend.users.models.UserReports
import com.koderkt.stray_animalsbackend.users.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
interface UserServices{
    fun createUser(user: User):Boolean
    fun updateUser(user: User): String

    fun getUsers():List<User>

    fun getUser(email:String ): Optional<User>
//    fun createNGO(ngo)


    fun updateReport(userReport: UserReports)

    fun getUserReports(userEmail: String):List<UserReports>

    fun joinAdoptionRequestList(adoptionID: String, userEmail: String, time:String)

    fun getAdoptionsPostsOfUser(userID: String): List<AdoptionPost>
}


