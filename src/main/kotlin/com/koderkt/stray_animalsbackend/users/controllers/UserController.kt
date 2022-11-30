package com.koderkt.stray_animalsbackend.users.controllers

import com.koderkt.stray_animalsbackend.users.models.User
import com.koderkt.stray_animalsbackend.users.models.UserReports
import com.koderkt.stray_animalsbackend.users.services.StorageService
import com.koderkt.stray_animalsbackend.users.services.UserServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController() {
    @Autowired
    lateinit var userServices: UserServices
    @Autowired
    lateinit var storageServices: StorageService
    @PostMapping("/user/create")
    fun createNewUser(@RequestBody user: User): ResponseEntity<HttpStatus>{
        return try {
//            val obj = storageServices.downloadFile(user.profileURL)
//            if(obj == null){
//                user.profileURL = "https://upload.wikimedia.org/wikipedia/en/0/0b/Darth_Vader_in_The_Empire_Strikes_Back.jpg"
//            }
            userServices.createUser(user)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e: Exception){
            println(e.message)
            ResponseEntity.ok(HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/user/report")
    fun updateReport(@RequestBody userReports: UserReports):ResponseEntity<HttpStatus>{
        return try {
            userServices.updateReport(userReports)
            ResponseEntity.ok(HttpStatus.ACCEPTED)
        }catch (e:Exception){
            ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/user/update")
    fun updateUser(@RequestBody user: User){
        userServices.updateUser(user)
    }



    @GetMapping("/user/get")
    fun getUser(@RequestParam email: String): ResponseEntity<User?>{
        val user = userServices.getUser(email)
        if(user.isPresent) {
            return ResponseEntity.ok(user.get())
        }
        return ResponseEntity.ok(null)
    }

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<User>>{
        val users = userServices.getUsers()
        return ResponseEntity.ok(users)
    }


}