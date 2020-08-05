package com.apetrov.kotlinrestapp.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity


@Entity
class Doctor(
        var specialty:String, name: String, surname: String, birthday: LocalDate, salary: BigDecimal
) : Employee(name, surname, birthday, salary) {
}