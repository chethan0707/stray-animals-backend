package com.koderkt.stray_animalsbackend.users.repositories

import com.koderkt.stray_animalsbackend.users.models.Event
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: MongoRepository<Event, String> {
}