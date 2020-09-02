package com.apetrov.petclinic.rest

import com.apetrov.petclinic.model.Reception
import com.apetrov.petclinic.rest.outdto.DoctorOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import com.apetrov.petclinic.service.DoctorService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.joda.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/doctor")
class DoctorControler (val doctorService: DoctorService){
    @ApiOperation("Получить список всех врачей в базе")
    @GetMapping
    fun getAllDoctors(): List<DoctorOutDto> {
        return doctorService.getAll()
    }
    @ApiOperation("Получить информацию по всем дням приема врача")
    @GetMapping("/{doctorId}/schedule")
    fun getReceptions(@PathVariable("doctorId")  doctorId:Long):List<ReceptionDayOutDto>{
        return doctorService.getDoctorsReceptions(doctorId)
    }

    @ApiOperation("Получить данные по конкретной дате приема")
    @GetMapping("/{doctorId}/schedule/date")
    fun getReceptionsByDate(@PathVariable("doctorId")  doctorId:Long,
                            @RequestParam @ApiParam("Дата в формате yyyy-MM-dd")
                            @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate):ReceptionDayOutDto?{
        return doctorService.getDoctorsReceptionsByDate(date ,doctorId )
    }
}