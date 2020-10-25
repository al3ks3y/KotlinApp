package com.apetrov.petclinic.rest

import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.service.ReceptionService
import com.apetrov.petclinic.util.RECEPTION_API_PREFIX
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.joda.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RECEPTION_API_PREFIX)
class ReceptionController(val receptionService: ReceptionService) {
    @ApiOperation("Получить информацию по всем дням приема врача")
    @GetMapping("/{doctorId}/schedule")
    fun getReceptions(@PathVariable("doctorId")  doctorId:Long):List<ReceptionDayOutDto>{
        return receptionService.getDoctorsReceptions(doctorId)
    }

    @ApiOperation("Получить данные по конкретной дате приема")
    @GetMapping("/{doctorId}/schedule/date")
    fun getReceptionsByDate(@PathVariable("doctorId") doctorId: Long,
                            @RequestParam @ApiParam("Дата в формате yyyy-MM-dd")
                            @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate): ReceptionDayOutDto? {
        return receptionService.getDoctorsReceptionsByDate(date, doctorId)
    }

    @Deprecated("На время пока нет личного кабинета клиента")
    @ApiOperation("Зарезервировать запись (может понадобиться для тестирования")
    @PutMapping("/{receptionId}")
    fun setOcupiedByRandomClient(@PathVariable("receptionId") receptionId: Long) {
        receptionService.setOcupiedByRandomClient(receptionId)
    }

    @ApiOperation("Зарезервировать запись (может понадобиться для тестирования")
    @GetMapping("/{receptionId}")
    fun getReceptionById(@PathVariable("receptionId") receptionId: Long) {
        receptionService.getReceptionById(receptionId)
    }
}