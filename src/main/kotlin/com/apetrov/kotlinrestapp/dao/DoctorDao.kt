package com.apetrov.kotlinrestapp.dao

import com.apetrov.kotlinrestapp.model.Doctor
import org.springframework.data.jpa.repository.JpaRepository

interface DoctorDao :JpaRepository<Doctor, Long> {
}