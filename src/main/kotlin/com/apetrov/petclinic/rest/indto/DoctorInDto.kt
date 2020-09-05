package com.apetrov.petclinic.rest.indto

import com.apetrov.petclinic.enums.Position
import org.joda.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal

data class DoctorInDto(
        val name:String,
        val surname:String,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val birthday: LocalDate,
        val salary:BigDecimal,
        val careerStartYear:Int,
        val specialization:String,
        val position: Position,
        val photoUrl: String,
        var branchId:Long?

) {
}