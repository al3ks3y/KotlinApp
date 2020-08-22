package com.apetrov.kotlinrestapp.rest.indto

import com.apetrov.kotlinrestapp.enums.Degree
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate

data class DoctorInDto(
        val name:String,
        val surname:String,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val birthday:LocalDate,
        val salary:BigDecimal,
        val careerStartYear:Int,
        val specialization:String,
        val degree: Degree

) {
}