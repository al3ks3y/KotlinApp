package com.apetrov.petclinic.rest

import com.apetrov.petclinic.rest.indto.DoctorInDto
import com.apetrov.petclinic.service.AdminService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class ServiceController (val adminService: AdminService){
    @PostMapping("/addDoc")
    fun addDoctor(doctorInDto: DoctorInDto){
        adminService.addDoctor(doctorInDto)
    }

}