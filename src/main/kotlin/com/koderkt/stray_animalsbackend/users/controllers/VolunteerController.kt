package com.koderkt.stray_animalsbackend.users.controllers

import com.koderkt.stray_animalsbackend.users.models.User
import com.koderkt.stray_animalsbackend.users.models.UserReports
import com.koderkt.stray_animalsbackend.users.models.Volunteer
import com.koderkt.stray_animalsbackend.users.services.NGOServices
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
    @Autowired
    lateinit var ngoServices: NGOServices
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
        println("Hello volunteer")
        if(user.isPresent) {
            println(user.get().userName)
            return ResponseEntity.ok(user.get())
        }
        return ResponseEntity.ok(null)
    }

    @GetMapping("/volunteers")
    fun getVolunteers(): ResponseEntity<List<Volunteer>>{
        val users = volunteerServices.getVolunteers()
        return ResponseEntity.ok(users)
    }



    @GetMapping("/volunteer/reports")
    fun getReports(@RequestParam email: String): ResponseEntity<MutableList<UserReports>>{
        return try{
            val reports: MutableList<UserReports> = mutableListOf()
            val volunteer = volunteerServices.getVolunteer(email).get()

            for(id in volunteer.reports){
                val report = volunteerServices.getUserReport(id)
                reports.add(report)
            }

            ResponseEntity.ok(reports)
        }
        catch (e: Exception){
            ResponseEntity.ok(mutableListOf())
        }
    }


    @PostMapping("/volunteer/status/update")
    fun updateStatus(@RequestParam email: String, @RequestParam status:Boolean): ResponseEntity<HttpStatus>{
        return try {
            volunteerServices.updateStatus(email, status)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/volunteer/report/close")
    fun updateReportStatus(@RequestParam id: String, @RequestParam volID: String, @RequestParam ngoID:String): ResponseEntity<HttpStatus>{
        return try{
            println("Helloupdate")

            volunteerServices.updateReportStatus(id, volID, ngoID)

            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/volunteer/event/join")
    fun joinEvent(@RequestParam volunteerEmail:String, @RequestParam eventId: String ): ResponseEntity<HttpStatus>{
        return try{
            println("Hello join")
            volunteerServices.joinEvent(volunteerEmail, eventId)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("/volunteer/join/ngo")
    fun joinNGO(@RequestParam email: String, @RequestParam ngoId: String ):ResponseEntity<HttpStatus>{
        return try{
            volunteerServices.joinNGO(email, ngoId)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}