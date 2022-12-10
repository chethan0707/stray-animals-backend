package com.koderkt.stray_animalsbackend.users.services

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Service
class StorageService {
    @Value("\${application.bucket.name}")
    private val bucketName: String? = null

    @Autowired
    private val s3Client: AmazonS3? = null
    fun uploadFile(file: MultipartFile): String {
        return try{
            val fileObj = convertMultiPartFileToFile(file)

            val fileName =  file.originalFilename
            println(fileName)
            if (fileName != null) {
                deleteFile(fileName)
            }
            s3Client!!.putObject(PutObjectRequest(bucketName, fileName, fileObj))
            fileObj.delete()
            println("File uploaded : $fileName")
            fileName.toString()
        }catch (e: Exception){
            ""
        }
    }

    fun downloadFile(fileName: String?): ByteArray? {
        try{
            val s3Object = s3Client!!.getObject(bucketName, fileName)
            val inputStream = s3Object.objectContent
            try {
                return IOUtils.toByteArray(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }catch (e: Exception){
            println(e.message)
            return null
        }
        return null
    }

    fun deleteFile(fileName: String): String {
        s3Client!!.deleteObject(bucketName, fileName)
        return "$fileName removed ..."
    }

    private fun convertMultiPartFileToFile(file: MultipartFile): File {
        val convertedFile = File(file.originalFilename)
        try {
            FileOutputStream(convertedFile).use { fos -> fos.write(file.bytes) }
        } catch (e: IOException) {
//            println("Error converting multipartFile to file", e)
        }
        return convertedFile
    }
}