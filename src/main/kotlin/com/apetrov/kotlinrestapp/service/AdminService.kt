package com.apetrov.kotlinrestapp.service

import com.apetrov.kotlinrestapp.dao.DoctorDao
import com.apetrov.kotlinrestapp.model.Doctor
import com.apetrov.kotlinrestapp.rest.indto.DoctorInDto
import org.springframework.stereotype.Service

@Service
class AdminService (val doctorDao: DoctorDao){
    fun addDoctor(dto: DoctorInDto){
        val doctor = Doctor(
                dto.specialization,
                dto.degree,
                dto.name,
                dto.surname,
                dto.birthday,
                dto.salary,
                dto.careerStartYear
        )
        doctorDao.save(doctor)
    }
}