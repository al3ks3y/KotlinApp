package com.apetrov.kotlinrestapp.service

import com.apetrov.kotlinrestapp.dao.DoctorDao
import com.apetrov.kotlinrestapp.model.Doctor
import org.springframework.stereotype.Service

@Service
class DoctorService (val doctorDao: DoctorDao){
    fun getAll():List<Doctor>{
       return doctorDao.findAll()
    }

}