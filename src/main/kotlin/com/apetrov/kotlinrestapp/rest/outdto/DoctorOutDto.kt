package com.apetrov.kotlinrestapp.rest.outdto

import com.apetrov.kotlinrestapp.enums.Degree
import com.apetrov.kotlinrestapp.model.Doctor
import java.time.LocalDate
import java.time.Period
import java.util.*

data class DoctorOutDto(
        var name:String,
        var surname:String,
        var age: Int,
        var experienceYears:Int,
        var specialization:String,
        var degree: Degree

) {
    constructor(doctor: Doctor):this(
            doctor.name,
            doctor.surname,
            Period.between(doctor.birthday, LocalDate.now()).years,
            Calendar.getInstance().get(Calendar.YEAR)-doctor.careerStartYear,
            doctor.specialization,
            doctor.degree){
    }
}