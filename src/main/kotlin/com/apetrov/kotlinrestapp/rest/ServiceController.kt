package com.apetrov.kotlinrestapp.rest

import com.apetrov.kotlinrestapp.rest.indto.DoctorInDto
import com.apetrov.kotlinrestapp.service.AdminService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class ServiceController (val adminService: AdminService){
    @PostMapping
    fun addDoctor(doctorInDto: DoctorInDto){
        adminService.addDoctor(doctorInDto)
    }

}