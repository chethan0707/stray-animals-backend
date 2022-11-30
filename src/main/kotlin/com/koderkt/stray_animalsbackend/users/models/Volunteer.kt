package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.annotation.Id

class Volunteer(
    @Id
    val id:String,
    val userName: String,
    val email:String,
    val phone: String,
    val rank:String,
    val rescueCount: Int,
    val ngos: List<String>
) {
}