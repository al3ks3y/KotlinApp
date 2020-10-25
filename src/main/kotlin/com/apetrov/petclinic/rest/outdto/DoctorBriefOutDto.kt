package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.model.Doctor

data class DoctorBriefOutDto(
        val name: String,
        val surname: String,
        val specialization: String
) {
    constructor(doctor: Doctor) : this(
            doctor.name,
            doctor.surname,
            doctor.specialization

    )
}