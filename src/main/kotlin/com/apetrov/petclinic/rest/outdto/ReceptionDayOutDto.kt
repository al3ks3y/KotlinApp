package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.model.Doctor
import org.joda.time.LocalDate

data class ReceptionDayOutDto (
        val doctor: DoctorOutDto,
        val date:LocalDate,
        val receptions: MutableList<ReceptionOutDto>
) {

}