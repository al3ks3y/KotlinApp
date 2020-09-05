package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.model.Reception
import org.joda.time.LocalDateTime

data class ReceptionOutDto (
        val id:Long?,
        val beginTime: LocalDateTime,
        val endTime: LocalDateTime,
        val ocupied: Boolean
) {constructor(reception: Reception):this(
        reception.id,
        reception.beginTime,
        reception.endTime,
        reception.client!=null
)

}