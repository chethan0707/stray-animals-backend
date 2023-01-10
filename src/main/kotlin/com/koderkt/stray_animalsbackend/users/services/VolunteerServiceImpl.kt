package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.UserReports
import com.koderkt.stray_animalsbackend.users.models.Volunteer
import com.koderkt.stray_animalsbackend.users.repositories.EventRepository
import com.koderkt.stray_animalsbackend.users.repositories.NGORepository
import com.koderkt.stray_animalsbackend.users.repositories.UserReportsRepository
import com.koderkt.stray_animalsbackend.users.repositories.VolunteerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VolunteerServiceImpl(
    private val volunteerRepository: VolunteerRepository,
    private val userReportsRepository: UserReportsRepository,
    private val eventRepository: EventRepository,
    private val ngoRepository: NGORepository
) : VolunteerServices {

    override fun createVolunteer(volunteer: Volunteer): Boolean {
        volunteer.ngos = ""
        volunteerRepository.save(volunteer)
        return true
    }

    override fun updateVolunteer(volunteer: Volunteer): String {
        return try {
            volunteerRepository.save(volunteer)
            "Updated successfully"
        }catch (e: Exception){
            println(e.message)
            ""
        }
    }

    override fun getVolunteers(): List<Volunteer> {
        return volunteerRepository.findAll()
    }

    override fun getVolunteer(email: String): Optional<Volunteer> {
        return volunteerRepository.findVolunteerByEmail(email)
    }

    override fun saveVolunteer(volunteer: Volunteer) {
        volunteerRepository.save(volunteer)
    }

    override fun getUserReport(id: String): UserReports {
        return userReportsRepository.findById(id).get()
    }

    override fun updateStatus(email: String, status: Boolean) {
        val vol = volunteerRepository.findVolunteerByEmail(email).get()
        vol.status = status
        volunteerRepository.save(vol)
    }

    override fun getVolunteersOfNGO(email: String): List<Volunteer> {
        return volunteerRepository.findAllByNgos(email)
    }

    override fun updateReportStatus(id: String, volId: String, ngoId: String) {
        try {
            val volunteer = volunteerRepository.findVolunteerByEmail(volId).get()

            volunteer.rescueCount += 1
            volunteerRepository.save(volunteer)

            val ngo = ngoRepository.findNGOByEmail(ngoId).get()

            ngo.rescueCount += 1
            ngoRepository.save(ngo)
            val report = userReportsRepository.findById(id).get()

            report.status = true
            userReportsRepository.save(report)
        }catch (e:Exception){
            println(e.message)
        }
    }

    override fun joinEvent(email: String, eventID: String) {
        try{
            val vol = volunteerRepository.findVolunteerByEmail(email).get()
            vol.events.add(eventID)
            val event = eventRepository.findById(eventID).get()
            event.volunteers.add(email)
            eventRepository.save(event)
            volunteerRepository.save(vol)
        }catch (e:Exception){
            println(e.message)
        }
    }


    override fun joinNGO(email: String, ngoId: String) {
        try{
            val volunteer = volunteerRepository.findVolunteerByEmail(email).get()
            volunteer.ngos = ngoId
            val ngo = ngoRepository.findNGOByEmail(email)

            volunteerRepository.save(volunteer)
        }catch (e:Exception){
            println(e.message)
        }
    }
}