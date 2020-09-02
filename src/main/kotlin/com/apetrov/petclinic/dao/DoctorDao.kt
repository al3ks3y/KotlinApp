package com.apetrov.petclinic.dao

import com.apetrov.petclinic.model.Doctor
import org.springframework.data.jpa.repository.JpaRepository

interface DoctorDao :JpaRepository<Doctor, Long> {
}