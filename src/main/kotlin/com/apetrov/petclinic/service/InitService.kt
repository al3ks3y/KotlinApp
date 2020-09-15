package com.apetrov.petclinic.service

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class InitService (val adminService: AdminService){

    @PostConstruct
    fun init(){
        adminService.initDoctorsAndUnits()
        adminService.initClients()
        adminService.initSchedule()
        adminService.initStartTime()
    }
}