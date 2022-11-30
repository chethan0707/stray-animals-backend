package com.koderkt.stray_animalsbackend.users.models

import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed

class NGODto (
    val name: String,
    val state: String,
    val zip: String,
    val city: String,
    val email: String,
    val phone:String,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val volunteers: List<String> = listOf(),
    val coordinates: List<Double>,
    val userReports: MutableList<UserReports>
        ){
}