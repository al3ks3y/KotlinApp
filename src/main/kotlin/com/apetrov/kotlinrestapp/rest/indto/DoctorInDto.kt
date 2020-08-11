package com.apetrov.kotlinrestapp.rest.indto

import com.apetrov.kotlinrestapp.model.Degree
import java.math.BigDecimal
import java.time.LocalDate

data class DoctorInDto(
        val name:String,
        val surname:String,
        val birthday:LocalDate,
        val salary:BigDecimal,
        val experienceYears:Int,
        val specialization:String,
        val degree: Degree

) {
}