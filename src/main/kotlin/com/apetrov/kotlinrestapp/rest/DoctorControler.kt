package com.apetrov.kotlinrestapp.rest

import com.apetrov.kotlinrestapp.model.Doctor
import com.apetrov.kotlinrestapp.rest.outdto.DoctorOutDto
import com.apetrov.kotlinrestapp.service.DoctorService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/doctor")
class DoctorControler (val doctorService: DoctorService){
    @ApiOperation("Получить список всех врачей в базе")
    @GetMapping
    fun getAllDoctors(): List<DoctorOutDto> {
        return doctorService.getAll()
    }
}