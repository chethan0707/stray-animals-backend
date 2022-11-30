package com.koderkt.stray_animalsbackend.users.repositories

import com.koderkt.stray_animalsbackend.users.models.NGO
import com.koderkt.stray_animalsbackend.users.models.NGODto
import com.koderkt.stray_animalsbackend.users.models.User
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface NGORepository:MongoRepository<NGO, String> {
    fun findByCoordinatesNear(coordinates: GeoJsonPoint): List<NGO>
    fun findByCity(city:String):List<NGO>
    fun findNGOByEmail(email: String):Optional<NGO>
    fun findNGOById(id: String?): NGO
}