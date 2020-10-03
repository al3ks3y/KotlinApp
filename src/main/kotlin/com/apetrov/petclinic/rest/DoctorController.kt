package com.apetrov.petclinic.rest

import com.apetrov.petclinic.model.Reception
import com.apetrov.petclinic.rest.outdto.BranchOutDto
import com.apetrov.petclinic.rest.outdto.DoctorOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import com.apetrov.petclinic.service.DoctorService
import com.apetrov.petclinic.util.DOCTOR_API_PREFIX
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.joda.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCTOR_API_PREFIX)
class DoctorController (val doctorService: DoctorService){
    @ApiOperation("Получить список всех врачей в базе")
    @GetMapping
    fun getAllDoctors(): List<DoctorOutDto> {
        return doctorService.getAll()
    }
    @ApiOperation("Получить список всех врачей в базе")
    @GetMapping("/search")
    fun findByName(@RequestParam searchStr:String): List<DoctorOutDto> {
        return doctorService.findDoctorByName(searchStr)
    }
    @ApiOperation("Получить список отделений")
    @GetMapping("/branches")
    fun getAllBranches():List<BranchOutDto>{
        return doctorService.getAllBranches()
    }
    @ApiOperation("Получить список врачей в отделении")
    @GetMapping("/branches/{branchId}")
    fun getDoctorsByBranch(@PathVariable("branchId") branchId:Long):List<DoctorOutDto>{
        return doctorService.findDoctorsByBranch(branchId)
    }
}