package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ngos")
class NGO(
    @Id
    val id: String?,
    val email: String,
    val name: String,
    val city:String,
    val address: Address,
    val phone:String,
    val role: String,
    val userReports: MutableList<UserReports>,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val coordinates: GeoJsonPoint?,
    val volunteers: MutableList<String>,
    val events: MutableList<String>,
    var rescueCount: Int
) {

}