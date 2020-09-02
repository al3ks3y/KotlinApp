package com.apetrov.petclinic.model

import lombok.ToString
import lombok.Value
import org.hibernate.annotations.Type
import org.joda.time.LocalDateTime
import javax.persistence.*

@Value
@Entity
@Table
@ToString
class Reception (
        var beginTime: LocalDateTime,
        var endTime:LocalDateTime,
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        var doctor: Doctor?=null,
        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        var client:Client?=null
):BaseEntity()