package com.apetrov.petclinic.rest

import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.service.ReceptionService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.joda.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reception")
class ReceptionController(val receptionService: ReceptionService) {
    @ApiOperation("Получить информацию по всем дням приема врача")
    @GetMapping("/{doctorId}/schedule")
    fun getReceptions(@PathVariable("doctorId")  doctorId:Long):List<ReceptionDayOutDto>{
        return receptionService.getDoctorsReceptions(doctorId)
    }

    @ApiOperation("Получить данные по конкретной дате приема")
    @GetMapping("/{doctorId}/schedule/date")
    fun getReceptionsByDate(@PathVariable("doctorId")  doctorId:Long,
                            @RequestParam @ApiParam("Дата в формате yyyy-MM-dd")
                            @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate): ReceptionDayOutDto?{
        return receptionService.getDoctorsReceptionsByDate(date ,doctorId )
    }
}