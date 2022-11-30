package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.*
import org.springframework.stereotype.Service
import java.util.*

interface NGOServices {

    fun createNGO(ngo: NGODto): String

    fun updateNGO(ngo: NGO): String

    fun deleteNGO(id: String) :String

    fun getNGOs():List<NGO>

    fun getNGO(email:String ): Optional<NGO>

    fun getNGOsByLocation(city: String):List<NGO>

    fun addEvent(event:  Event, ngoEmail:String)

    fun getEvents(email: String): List<Event>

    fun getReports(email: String): List<UserReports>

}