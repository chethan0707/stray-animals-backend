package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.Volunteer
import com.koderkt.stray_animalsbackend.users.repositories.VolunteerRepository
import org.springframework.stereotype.Service
import java.util.*
@Service
class VolunteerServiceImpl(private val volunteerRepository: VolunteerRepository):VolunteerServices {

   override fun createVolunteer(volunteer: Volunteer): Boolean {
        volunteer.ngos = mutableListOf()
        volunteerRepository.save(volunteer)
       return true
    }

    override fun updateVolunteer(volunteer: Volunteer): String {
TODO()
    }

    override fun getVolunteers(): List<Volunteer> {
        return volunteerRepository.findAll()
    }

    override fun getVolunteer(email: String): Optional<Volunteer> {
        return volunteerRepository.findVolunteerByEmail(email)
    }
}