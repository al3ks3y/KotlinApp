package com.apetrov.petclinic.model

import lombok.ToString
import lombok.Value
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Value
@Entity
@Table
@ToString
class Client (
        var name: String,
        var surname: String,
        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        var receptions:MutableList<Reception> = arrayListOf()
):BaseEntity()