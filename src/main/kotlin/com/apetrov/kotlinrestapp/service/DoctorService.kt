package com.apetrov.kotlinrestapp.service

import com.apetrov.kotlinrestapp.dao.DoctorDao
import com.apetrov.kotlinrestapp.model.Doctor
import com.apetrov.kotlinrestapp.rest.outdto.DoctorOutDto
import org.springframework.stereotype.Service

@Service
class DoctorService (val doctorDao: DoctorDao){
    fun getAll():List<DoctorOutDto>{
       return doctorDao.findAll().map { DoctorOutDto(it) }
    }
}