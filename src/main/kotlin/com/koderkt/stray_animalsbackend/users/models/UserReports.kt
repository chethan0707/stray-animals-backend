package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user_reports")
class UserReports(
    @Id
    var caseId: String,
    var userId: String,
    var coordinates: List<Double>,
    var status: Boolean,
    var urls: List<String>,
    var ngoId: String,
    var description: String
)