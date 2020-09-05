package com.apetrov.petclinic.model

import lombok.ToString
import lombok.Value
import javax.persistence.*

@Value
@Entity
@Table
@ToString
class Branch (
        var name:String,
        @OneToMany(cascade = [CascadeType.ALL])
        var doctors:MutableList<Doctor> = arrayListOf()
):BaseEntity()
