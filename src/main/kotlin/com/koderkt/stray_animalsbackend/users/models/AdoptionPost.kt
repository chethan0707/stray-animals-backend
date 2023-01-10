package com.koderkt.stray_animalsbackend.users.models

import nonapi.io.github.classgraph.json.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "adoptions")
class AdoptionPost(
    @Id
    val id: String?,
    val ngoID: String,
    var status: Boolean,
    var adoptedBy:String,
    val title: String,
    val description: String,
    val requestList: MutableMap<String,String>,
    val imageUrls: MutableList<String>
) {
}