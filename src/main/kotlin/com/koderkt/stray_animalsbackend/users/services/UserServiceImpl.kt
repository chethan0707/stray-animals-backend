package com.koderkt.stray_animalsbackend.users.services

import com.koderkt.stray_animalsbackend.users.models.User
import com.koderkt.stray_animalsbackend.users.models.UserReports
import com.koderkt.stray_animalsbackend.users.repositories.UserReportsRepository
import com.koderkt.stray_animalsbackend.users.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userReportsRepository: UserReportsRepository,

    ) : UserServices {
    override fun createUser(user: User): Boolean {
        user.userReports = mutableListOf()
        userRepository.save(user)
        return true
    }

    override fun updateUser(user: User): String {
        return try {
            val exiUser: User = userRepository.findUserById(user.id)
            exiUser.email = user.email
            exiUser.phone = user.phone
            exiUser.userName = user.userName
            "User updated successfully"
        } catch (e: Exception) {
            (e.message.toString())
        }
    }


    override fun getUser(email: String): Optional<User> {
        return userRepository.findUserByEmail(email)
    }


    override fun updateReport(userReport: UserReports) {
        try {
            val userEmail = userReport.userId
            val userInfo = userRepository.findUserByEmail(userEmail)
            val user = userInfo.get()
            userReportsRepository.save(userReport)
            user.userReports.add(userReport.caseId)
            userRepository.save(user)
        }catch (e: Exception){
            println(e.message)
        }
    }

    override fun getUserReports(userEmail: String): List<UserReports> {
        return userReportsRepository.findAllByuserId(userEmail)
    }

    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }
}