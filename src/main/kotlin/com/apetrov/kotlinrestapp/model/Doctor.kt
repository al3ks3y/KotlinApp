package com.apetrov.kotlinrestapp.model

import com.apetrov.kotlinrestapp.enums.Degree
import lombok.ToString
import lombok.Value
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table

@Value
@Entity
@Table
@ToString
class Doctor(
        var specialization:String,
        var degree: Degree,
        var name: String,
        var surname: String,
        var birthday: LocalDate,
        var salary: BigDecimal,
        var careerStartYear:Int
):BaseEntity()