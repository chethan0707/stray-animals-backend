package com.koderkt.stray_animalsbackend.users.repositories

import com.koderkt.stray_animalsbackend.users.models.User
import com.koderkt.stray_animalsbackend.users.models.Volunteer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VolunteerRepository: MongoRepository<Volunteer, String> {
    fun findVolunteerByEmail(email:String): Optional<Volunteer>
}