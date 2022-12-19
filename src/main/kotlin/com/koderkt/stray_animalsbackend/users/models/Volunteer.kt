package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "volunteers")
class Volunteer(
    @Id
    var id : String?,
    var userName: String,
    var email:String,
    var phone: String,
    var rescueCount: Int,
    var city: String,
    var ngos: MutableList<String>
) {
}