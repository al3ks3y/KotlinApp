package com.apetrov.kotlinrestapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinRestappApplication

fun main(args: Array<String>) {
	runApplication<KotlinRestappApplication>(*args)
}
