package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date
@Document("events")
class Event(
    @Id
    var eventId: String,
    var eventName: String,
    var date: String,
    var time: String,
    var coordinates: List<Double>,
    var description: String,
    var images: List<String>,
    var status: Boolean,
    var volunteersRequiredCount : Int,
    var volunteers: MutableList<String>,
) {

}