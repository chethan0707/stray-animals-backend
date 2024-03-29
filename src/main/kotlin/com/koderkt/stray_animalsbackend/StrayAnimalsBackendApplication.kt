package com.koderkt.stray_animalsbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class StrayAnimalsBackendApplication
fun main(args: Array<String>) {
    runApplication<StrayAnimalsBackendApplication>(*args)
}
