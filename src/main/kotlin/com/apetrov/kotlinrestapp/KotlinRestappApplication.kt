package com.apetrov.kotlinrestapp

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableAutoConfiguration
class KotlinRestappApplication

fun main(args: Array<String>) {
	runApplication<KotlinRestappApplication>(*args)
}
