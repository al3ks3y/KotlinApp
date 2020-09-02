package com.apetrov.petclinic

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableAutoConfiguration
class PetClinicApplication

fun main(args: Array<String>) {
	runApplication<PetClinicApplication>(*args)
}
