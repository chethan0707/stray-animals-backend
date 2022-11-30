package com.koderkt.stray_animalsbackend.users.controllers

import com.koderkt.stray_animalsbackend.users.models.*
import com.koderkt.stray_animalsbackend.users.models.dto.EventDTO
import com.koderkt.stray_animalsbackend.users.services.NGOServices
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class NGOController {

    @Autowired
    lateinit var ngoServices: NGOServices

    @PostMapping("/ngo/create")
    fun createNGO(@RequestBody ngo: NGODto): ResponseEntity<HttpStatus> {
        return try{
            ngoServices.createNGO(ngo)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: Exception){
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
            ResponseEntity.ok(ngoServices.getNGOs())
        } catch (e: Exception) {
            ResponseEntity.ok(listOf())
        }
    }


    @GetMapping("/ngo/get")
    fun getUser(@RequestParam email: String): ResponseEntity<NGO?>{
        println(email)
        val ngo = ngoServices.getNGO(email)
        if(ngo.isPresent) {
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
    fun getNGOsByLocation(@RequestParam city:String):ResponseEntity<List<NGO>>{
        return ResponseEntity.ok(ngoServices.getNGOsByLocation(city))
    }



    @PostMapping("/ngo/event/add")
    fun addEvent(@RequestBody eventDto:EventDTO):ResponseEntity<HttpStatus>{
        return try{
            ngoServices.addEvent(eventDto.event, eventDto.ngoEmail)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: java.lang.Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/ngo/events")
    fun getEvents(@RequestParam("email") email: String):ResponseEntity<List<Event>>{
        return try{
            ResponseEntity.ok(ngoServices.getEvents(email))
        }catch (e: Exception){
            println(e.message)
            ResponseEntity.ok(mutableListOf())
        }
    }

    @GetMapping("/ngo/reports/get")
    fun getReports(@RequestParam("email") email: String): ResponseEntity<List<UserReports>>{
        return try{
            ResponseEntity.ok(ngoServices.getReports(email))
        }catch(e: Exception) {
            ResponseEntity.ok(mutableListOf())
        }
    }
}