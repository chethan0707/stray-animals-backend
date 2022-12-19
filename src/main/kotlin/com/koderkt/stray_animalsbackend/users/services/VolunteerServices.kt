package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.Volunteer
import com.koderkt.stray_animalsbackend.users.repositories.VolunteerRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface VolunteerServices {
    fun createVolunteer(volunteer: Volunteer): Boolean
    fun updateVolunteer(volunteer: Volunteer): String

    fun getVolunteers(): List<Volunteer>

    fun getVolunteer(email: String): Optional<Volunteer>

}