package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.model.Client
import com.apetrov.petclinic.model.Reception
import org.joda.time.LocalDateTime

data class ReceptionOutDto(
        val id: Long?,
        val beginTime: LocalDateTime,
        val endTime: LocalDateTime,
        val ocupied: Boolean,
        val client: ClientOutDto?,
        val doctor: DoctorBriefOutDto?
) {
    constructor(reception: Reception, client: Client?) : this(
            reception.id,
            reception.beginTime,
            reception.endTime,
            reception.client != null,
            if (client == reception.client) ClientOutDto(reception.client) else null,
            if (reception.doctor != null) DoctorBriefOutDto(reception.doctor!!) else null
    )

    constructor(reception: Reception) : this(
            reception.id,
            reception.beginTime,
            reception.endTime,
            reception.client != null,
            null,
            if (reception.doctor != null) DoctorBriefOutDto(reception.doctor!!) else null)
}