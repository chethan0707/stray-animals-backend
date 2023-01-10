package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.*
import com.koderkt.stray_animalsbackend.users.models.dto.AssignVolunteerDTO
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface NGOServices {

    fun createNGO(ngo: NGODto): String

    fun updateNGO(ngo: NGO): String

    fun deleteNGO(id: String) :String

    fun getNGOs():List<NGO>

    fun getNGO(email:String ): Optional<NGO>

    fun getNGOsByLocation(city: String):List<NGO>

    fun addEvent(event:  Event, ngoEmail:String)

    fun updateEvent(event:  Event, ngoEmail:String)

    fun getEvents(email: String): List<Event>

    fun getReports(email: String): List<UserReports>

    fun assignVolunteer(assignVolunteerDTO: AssignVolunteerDTO)

    fun getVolunteers(email: String) : List<Volunteer>

    fun deleteVolunteer( volEmail: String, ngoEmail: String)

    fun getVolunteersOfEvent(volEmails: List<String>) :MutableList<Volunteer>

    fun addAdoptionPost(adoptionPost: AdoptionPost)

    fun getAdoptionRequestUsers(id: String): List<User>
    fun getAdoptions(ngoEMail:String):List<AdoptionPost>
    fun updateAdoptionStatus(id: String, userEmail: String)
}