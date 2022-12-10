package com.koderkt.stray_animalsbackend.users.controllers

import com.koderkt.stray_animalsbackend.users.services.StorageService
import com.koderkt.stray_animalsbackend.utils.JWTUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/file")
class StorageController {
    @Autowired
    lateinit var service: StorageService
    @PostMapping("/upload")
    fun uploadFile(@RequestParam(value = "file") file: MultipartFile?): ResponseEntity<String> {
        val message = file?.let { service.uploadFile(it) }
        return ResponseEntity.ok(message)
    }
    @GetMapping("/download/{fileName}")
    fun downloadFile(@PathVariable fileName: String): ResponseEntity<ByteArrayResource?>? {
        println(fileName)
        val data: ByteArray? = service.downloadFile(fileName)
        val resource = data?.let { ByteArrayResource(it) }
        if (data != null) {
            return ResponseEntity
                .ok()
                .contentLength(data.size.toLong())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"$fileName\"")
                .body(resource)
        }
        return null
    }

    @DeleteMapping("/delete/{fileName}")
    fun deleteFile(@PathVariable fileName: String?): ResponseEntity<String?> {
        return ResponseEntity(fileName?.let { service.deleteFile(it) }, HttpStatus.OK)
    }
}