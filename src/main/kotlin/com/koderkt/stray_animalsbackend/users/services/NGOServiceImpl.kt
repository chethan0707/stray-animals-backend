package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.*
import com.koderkt.stray_animalsbackend.users.repositories.EventRepository
import com.koderkt.stray_animalsbackend.users.repositories.NGORepository
import com.koderkt.stray_animalsbackend.users.repositories.UserReportsRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*

@Service
class NGOServiceImpl : NGOServices {
    @Autowired
    lateinit var ngoRepository: NGORepository
@Autowired
lateinit var userReportsRepository: UserReportsRepository
    @Autowired
    lateinit var eventRepository: EventRepository
    override fun createNGO(newNGO: NGODto): String {
        return try {
            val exi = ngoRepository.findNGOByEmail(newNGO.email)
            if (!exi.isEmpty) {
                return "Email already registered"
            }
            val address = Address(zipCode = newNGO.zip, state = newNGO.state)
            val ngo = NGO(
                city = newNGO.city,
                id = ObjectId().toString(),
                name = newNGO.name,
                address = address,
                volunteers = mutableListOf(),
                coordinates = GeoJsonPoint(Point(newNGO.coordinates[0], newNGO.coordinates[1])),
                email = newNGO.email,
                phone = newNGO.phone,
                role = "NGO",
                userReports = mutableListOf(),
                events = mutableListOf()
            )
            ngoRepository.save(ngo)
            "NGO created successfully"
        } catch (e: Exception) {
            println(e.message)
            "Unknown error occurred"
        }
    }

    override fun updateNGO(ngo: NGO): String {
        return try {
            var exiNGO: NGO = ngoRepository.findNGOById(ngo.id)
            exiNGO = ngo
            "User updated successfully"
        } catch (e: Exception) {
            (e.message.toString())
        }
    }

    override fun deleteNGO(id: String): String {
        return try {
            ngoRepository.deleteById(id)
            "Deleted successfully"
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override fun getNGO(email: String): Optional<NGO> {
        return ngoRepository.findNGOByEmail(email)
    }

    override fun getNGOs(): List<NGO> {
        return ngoRepository.findAll()
    }

    override fun getNGOsByLocation(city: String): List<NGO> {
        return ngoRepository.findByCity(city)
    }

    override fun getEvents(email: String): List<Event> {
        val ngo = ngoRepository.findNGOByEmail(email)
        val ngoInfo =  ngo.get()
        val events = mutableListOf<Event>()
        for(eventId in ngoInfo.events){
            val event = eventRepository.findById(eventId)
            events.add(event.get())
        }
        return events
    }

    override fun addEvent(event: Event, ngoEmail: String) {
        val ngo = ngoRepository.findNGOByEmail(ngoEmail)
        eventRepository.save(event)
        val ngoInfo = ngo.get()
        ngoInfo.events.add(event.eventId)
        ngoRepository.save(ngoInfo)
    }

    override fun getReports(email: String): List<UserReports> {
        return userReportsRepository.findByngoId(email)
    }

}