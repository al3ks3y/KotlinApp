package com.apetrov.petclinic.dao

import com.apetrov.petclinic.model.Doctor
import com.apetrov.petclinic.model.Reception
import org.springframework.data.jpa.repository.JpaRepository

interface ReceptionDao:JpaRepository<Reception,Long> {
    fun findByDoctor(doctor: Doctor):List<Reception>
}