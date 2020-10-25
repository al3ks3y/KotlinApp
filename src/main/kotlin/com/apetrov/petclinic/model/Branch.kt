package com.apetrov.petclinic.model

import lombok.ToString
import lombok.Value
import javax.persistence.*

@Value
@Entity
@Table
@ToString
class Branch(
        var name: String,
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "branchId")
        var doctors: MutableList<Doctor> = arrayListOf()
):BaseEntity()
