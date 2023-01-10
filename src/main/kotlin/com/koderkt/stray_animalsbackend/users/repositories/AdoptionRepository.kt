package com.koderkt.stray_animalsbackend.users.repositories

import com.koderkt.stray_animalsbackend.users.models.AdoptionPost
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AdoptionRepository : MongoRepository<AdoptionPost, String>{
    fun getAdoptionPostByngoID(ngoEmail:String):List<AdoptionPost>

}