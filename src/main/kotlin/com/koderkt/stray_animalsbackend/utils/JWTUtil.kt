package com.koderkt.stray_animalsbackend.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Service

@Service
class JWTUtil {

    private val algorithm: Algorithm = Algorithm.HMAC256("secret".toByteArray())
    fun signToken(email: String): String {
        return JWT.create().withSubject(email).sign(algorithm)
    }

    fun verifyToken(token: String):Boolean{
        println(token)
        val algorithm = Algorithm.HMAC256("secret".toByteArray())
        val verifier = JWT.require(algorithm).build()
        val decodedJWT = verifier.verify(token)
        return false
    }
}