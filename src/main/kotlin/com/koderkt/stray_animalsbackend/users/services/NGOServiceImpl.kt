package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.*
import com.koderkt.stray_animalsbackend.users.models.dto.AssignVolunteerDTO
import com.koderkt.stray_animalsbackend.users.repositories.*
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.stereotype.Service
import java.util.*

@Service
class NGOServiceImpl : NGOServices {
    @Autowired
    lateinit var ngoRepository: NGORepository
    @Autowired
    lateinit var userReportsRepository: UserReportsRepository
    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var adoptionRepository: AdoptionRepository
    @Autowired
    lateinit var volunteerServices: VolunteerServices

    @Autowired
    lateinit var userRepository: UserRepository
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
                rescueCount = 0,
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
            ngoRepository.save(ngo)
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

    override fun updateEvent(event: Event, ngoEmail: String) {
        val exiEvent = eventRepository.findById(event.eventId).get()
        exiEvent.eventName = event.eventName
        exiEvent.date = event.date
        exiEvent.status = event.status
        exiEvent.coordinates = event.coordinates
        exiEvent.description = event.description
        exiEvent.time = event.time
        exiEvent.images = event.images

        exiEvent.volunteersRequiredCount = event.volunteersRequiredCount
        exiEvent.volunteers = event.volunteers
        eventRepository.save(exiEvent)
    }


    override fun getReports(email: String): List<UserReports> {
        return userReportsRepository.findByngoId(email)
    }
    override fun assignVolunteer(assignVolunteerDTO: AssignVolunteerDTO) {
        val volunteer = volunteerServices.getVolunteer(assignVolunteerDTO.volunteerId).get()
        val report = userReportsRepository.findById(assignVolunteerDTO.reportId).get()
        report.volunteer = assignVolunteerDTO.volunteerId
        userReportsRepository.save(report)
        volunteer.reports.add(assignVolunteerDTO.reportId)
        volunteerServices.saveVolunteer(volunteer)
    }

    override fun getVolunteers(email: String): List<Volunteer> {
        return volunteerServices.getVolunteersOfNGO(email)
    }

    override fun deleteVolunteer(volEmail: String, ngoEmail: String) {
        try{

            val volunteer = volunteerServices.getVolunteer(volEmail).get()
            volunteer.ngos = ""
            volunteer.reports = mutableListOf()
            volunteer.events = mutableListOf()
            volunteerServices.saveVolunteer(volunteer)
            val ngo = ngoRepository.findNGOByEmail(ngoEmail).get()
            ngo.volunteers.remove(volEmail)
            ngoRepository.save(ngo)
        }catch (e:Exception){
            println(e.message)
        }
    }

    override fun getVolunteersOfEvent(volEmails: List<String>): MutableList<Volunteer> {
        val volunteers = mutableListOf<Volunteer>()
        for (i in volEmails){
            volunteers.add(volunteerServices.getVolunteer(i).get())
        }
        return volunteers
    }

    override fun getAdoptions(ngoEMail: String): List<AdoptionPost> {
        return adoptionRepository.getAdoptionPostByngoID(ngoEMail)
    }

    override fun updateAdoptionStatus(id: String, userEmail: String) {
        val adoption = adoptionRepository.findById(id).get()
        adoption.status = true
        adoption.adoptedBy = userEmail
        adoptionRepository.save(adoption)

    }

    override fun addAdoptionPost(adoptionPost: AdoptionPost) {
        adoptionRepository.save(adoptionPost)
    }

    override fun getAdoptionRequestUsers(id: String): MutableList<User> {
        val users = mutableListOf<User>()
        val adoptionPost = adoptionRepository.findById(id).get()
        for (key in adoptionPost.requestList.keys){
                val user = userRepository.findUserById(key)
                users.add(user)
        }
        return users
    }
}