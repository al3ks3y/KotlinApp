package com.apetrov.kotlinrestapp.rest

import com.apetrov.kotlinrestapp.model.Doctor
import com.apetrov.kotlinrestapp.service.DoctorService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/doctor")
class DoctorControler (val doctorService: DoctorService){

    fun getAllDoctors(): List<Doctor> {
        return doctorService.getAll();
    }
}