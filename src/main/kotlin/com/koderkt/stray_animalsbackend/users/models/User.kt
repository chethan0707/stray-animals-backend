package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User(
    @Id
    var id: String?,
    var userName: String,
    var phone: String,
    var role: String,
    var email: String,
    var profileURL: String,
    var userReports: MutableList<String>
) {
}