package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.UserReports
import com.koderkt.stray_animalsbackend.users.models.Volunteer
import com.koderkt.stray_animalsbackend.users.repositories.VolunteerRepository
import org.apache.coyote.http11.filters.VoidOutputFilter
import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface VolunteerServices {
    fun createVolunteer(volunteer: Volunteer): Boolean
    fun updateVolunteer(volunteer: Volunteer): String

    fun getVolunteers(): List<Volunteer>

    fun getVolunteer(email: String): Optional<Volunteer>
    fun saveVolunteer(volunteer: Volunteer)

    fun getUserReport(email: String): UserReports

    fun updateStatus(email: String, status: Boolean)

    fun getVolunteersOfNGO(email: String): List<Volunteer>

    fun updateReportStatus(id: String, volId: String, ngoId: String)

    fun joinEvent(email: String, eventID: String)


    fun joinNGO(email: String, ngoId: String)
}