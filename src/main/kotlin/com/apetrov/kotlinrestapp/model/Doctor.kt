package com.apetrov.kotlinrestapp.model

import lombok.Value
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table

@Value
@Entity
@Table
class Doctor(
        var specialty:String,
        var degree: Degree,
        name: String,
        surname: String,
        birthday: LocalDate,
        salary: BigDecimal,
        experienceYears:Int
) : Employee(name, surname, birthday, salary, experienceYears) {
}