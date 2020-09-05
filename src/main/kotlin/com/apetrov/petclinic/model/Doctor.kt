package com.apetrov.petclinic.model

import com.apetrov.petclinic.enums.Position
import lombok.ToString
import lombok.Value
import org.joda.time.LocalDate
import java.math.BigDecimal
import javax.persistence.*

@Value
@Entity
@Table
@ToString
class Doctor(
        var specialization:String,
        var position: Position,
        var name: String,
        var surname: String,
        var birthday: LocalDate,
        var salary: BigDecimal,
        var careerStartYear:Int,
        var photoUrl:String,
        @ManyToOne(cascade = [CascadeType.ALL])
        var branch: Branch,
        @OneToMany(cascade = [CascadeType.ALL])
        var receptions:MutableList<Reception> = arrayListOf()

):BaseEntity()