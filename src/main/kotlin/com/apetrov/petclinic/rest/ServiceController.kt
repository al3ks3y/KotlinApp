package com.apetrov.petclinic.rest

import com.apetrov.petclinic.rest.indto.DoctorInDto
import com.apetrov.petclinic.service.AdminService
import com.apetrov.petclinic.util.ADMIN_API_PREFIX
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ADMIN_API_PREFIX)
class ServiceController (val adminService: AdminService){
    @ApiOperation("Док одномоментно добавляется в базу. При перезапуске сервиса сотрется")
    @PostMapping("/addDoc")
    fun addDoctor(@RequestBody doctorInDto: DoctorInDto){
        adminService.addDoctor(doctorInDto)
    }
    @ApiOperation("Получить аптайм в днях (целых)")
    @GetMapping("/uptime")
    fun getUptime() :String{
       return adminService.getUptime()
    }

}