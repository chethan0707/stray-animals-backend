package com.koderkt.stray_animalsbackend.users.models.dto

import com.koderkt.stray_animalsbackend.users.models.Event

class EventDTO(
    val event:Event,
    val ngoEmail: String
) {
}