package com.apetrov.petclinic.rest

import com.apetrov.petclinic.rest.indto.DoctorInDto
import com.apetrov.petclinic.service.AdminService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class ServiceController (val adminService: AdminService){
    @ApiOperation("Док одномоментно добавляется в базу. При перезапуске сервиса сотрется")
    @PostMapping("/addDoc")
    fun addDoctor(doctorInDto: DoctorInDto){
        adminService.addDoctor(doctorInDto)
    }

}