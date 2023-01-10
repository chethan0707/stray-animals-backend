package com.koderkt.stray_animalsbackend.users.controllers

import com.koderkt.stray_animalsbackend.users.models.*
import com.koderkt.stray_animalsbackend.users.models.dto.AssignVolunteerDTO
import com.koderkt.stray_animalsbackend.users.models.dto.EventDTO
import com.koderkt.stray_animalsbackend.users.models.dto.VolunteersRequestDTO
import com.koderkt.stray_animalsbackend.users.services.NGOServices
import org.apache.http.protocol.ResponseServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.*
import kotlin.math.log

@RestController
@RequestMapping("/api")
class NGOController {
    @Autowired
    lateinit var ngoServices: NGOServices

    @PostMapping("/ngo/create")
    fun createNGO(@RequestBody ngo: NGODto): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.createNGO(ngo)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity.ok(HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/ngo/update")
    fun updateNGO(@RequestBody ngo: NGO): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.updateNGO(ngo)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/ngos")
    fun getNGOs(): ResponseEntity<List<NGO>> {
        return try {
            println("Hello")
            ResponseEntity.ok(ngoServices.getNGOs())
        } catch (e: Exception) {
            ResponseEntity.ok(listOf())
        }
    }

    @GetMapping("/ngo/get")
    fun getUser(@RequestParam email: String): ResponseEntity<NGO?> {
        println(email)
        val ngo = ngoServices.getNGO(email)
        if (ngo.isPresent) {
            println(ngo.get().role)
            return ResponseEntity.ok(ngo.get())
        }
        return ResponseEntity.ok(null)
    }

    @DeleteMapping("/ngo/delete")
    fun deleteNGO(@RequestParam id: String): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.deleteNGO(id)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/ngo/location")
    fun getNGOsByLocation(@RequestParam city: String): ResponseEntity<List<NGO>> {
        return ResponseEntity.ok(ngoServices.getNGOsByLocation(city))
    }

    @PostMapping("/ngo/event/add")
    fun addEvent(@RequestBody eventDto: EventDTO): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.addEvent(eventDto.event, eventDto.ngoEmail)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: java.lang.Exception) {
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("/ngo/event/update")
    fun updateEvent(@RequestBody eventDto: EventDTO): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.updateEvent(eventDto.event, eventDto.ngoEmail)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: java.lang.Exception) {
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/ngo/events")
    fun getEvents(@RequestParam("email") email: String): ResponseEntity<List<Event>> {
        return try {
            ResponseEntity.ok(ngoServices.getEvents(email))
        } catch (e: Exception) {
            println(e.message)
            ResponseEntity.ok(mutableListOf())
        }
    }

    @GetMapping("/ngo/reports/get")
    fun getReports(@RequestParam("email") email: String): ResponseEntity<List<UserReports>> {
        return try {
            ResponseEntity.ok(ngoServices.getReports(email))
        } catch (e: Exception) {
            ResponseEntity.ok(mutableListOf())
        }
    }


    @PostMapping("/ngo/report/volunteer/assign")
    fun assignVolunteer(@RequestBody assignVolunteerDTO: AssignVolunteerDTO): ResponseEntity<HttpStatus> {
        return try {
            println("Assign volunteer")
            ngoServices.assignVolunteer(assignVolunteerDTO)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: Exception) {

            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @GetMapping("/ngo/volunteers")
    fun getAllVolunteers(@RequestParam email: String): ResponseEntity<List<Volunteer>> {
        return try {
            ResponseEntity.ok(ngoServices.getVolunteers(email))
        } catch (e: Exception) {
            ResponseEntity.ok(mutableListOf())
        }
    }

    @DeleteMapping("/ngo/remove/volunteer")
    fun deleteVolunteer(@RequestParam volEmail: String, @RequestParam ngoEmail: String): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.deleteVolunteer(volEmail, ngoEmail)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/ngo/event/volunteers")
    fun getNgoEventVolunteers(@RequestBody emails: VolunteersRequestDTO): ResponseEntity<MutableList<Volunteer>> {
        return try {
            ResponseEntity.ok(ngoServices.getVolunteersOfEvent(emails.emails))
        } catch (e: Exception) {
            ResponseEntity.ok(mutableListOf())
        }
    }


    @PostMapping("/ngo/add/adoption")
    fun addAdoptionPost(@RequestBody adoptionPost: AdoptionPost): ResponseEntity<HttpStatus> {
        return try {
            ngoServices.addAdoptionPost(adoptionPost)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            println(e.message)
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/ngo/adoptions")
    fun getAllAdoptions(@RequestParam ngoEmail: String): ResponseEntity<List<AdoptionPost>> {
        return try {
            ResponseEntity.ok(ngoServices.getAdoptions(ngoEmail))
        } catch (e: Exception) {
            println(e.message)
            ResponseEntity.ok(listOf())
        }
    }


    @GetMapping("/ngo/adoption/requests")
    fun getUsers(@RequestParam id: String): ResponseEntity<List<User>> {
        return try {
            ResponseEntity.ok(ngoServices.getAdoptionRequestUsers(id))
        } catch (e: Exception) {
            println(e.message)
            ResponseEntity.ok(listOf<User>())
        }
    }

        @GetMapping("/ngo/adoption/close")
    fun updateAdoptionStatus(@RequestParam id:String, @RequestParam userEmail: String):ResponseEntity<HttpStatus>{
        return try{
            ngoServices.updateAdoptionStatus(id, userEmail)
            ResponseEntity.ok(HttpStatus.ACCEPTED)

        }catch (e: Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}