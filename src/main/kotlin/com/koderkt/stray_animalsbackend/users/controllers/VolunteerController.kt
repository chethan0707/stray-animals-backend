package com.koderkt.stray_animalsbackend.users.controllers

import com.koderkt.stray_animalsbackend.users.models.User
import com.koderkt.stray_animalsbackend.users.models.Volunteer
import com.koderkt.stray_animalsbackend.users.services.VolunteerServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class VolunteerController {
    @Autowired
    lateinit var volunteerServices: VolunteerServices
    @PostMapping("/volunteer/create")
    fun createVolunteer(@RequestBody volunteer: Volunteer):ResponseEntity<HttpStatus>{
        return try {

            println(volunteer.email)
            volunteerServices.createVolunteer(volunteer)
            ResponseEntity.ok(HttpStatus.ACCEPTED)

        }catch(e: Exception){
            println(e.message)
            ResponseEntity.ok(HttpStatus.BAD_REQUEST)
        }
    }


    @GetMapping("/volunteer/get")
    fun getVolunteer(@RequestParam email: String): ResponseEntity<Volunteer?>{
        val user = volunteerServices.getVolunteer(email)
        if(user.isPresent) {
            return ResponseEntity.ok(user.get())
        }
        return ResponseEntity.ok(null)
    }

    @GetMapping("/volunteers")
    fun getVolunteers(): ResponseEntity<List<Volunteer>>{
        val users = volunteerServices.getVolunteers()
        return ResponseEntity.ok(users)
    }
}