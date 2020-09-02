package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.enums.Position
import com.apetrov.petclinic.model.Doctor
import org.joda.time.LocalDate
import org.joda.time.Period
import java.util.*

data class DoctorOutDto(
        var id:Long?,
        var name:String,
        var surname:String,
        var age: Int,
        var experienceYears:Int,
        var specialization:String,
        var position: Position,
        var photoUrl: String

) {
    constructor(doctor: Doctor):this(
            doctor.id!!,
            doctor.name,
            doctor.surname,
            Period(doctor.birthday, LocalDate.now()).years,
            Calendar.getInstance().get(Calendar.YEAR)-doctor.careerStartYear,
            doctor.specialization,
            doctor.position,
            doctor.photoUrl){
    }
}