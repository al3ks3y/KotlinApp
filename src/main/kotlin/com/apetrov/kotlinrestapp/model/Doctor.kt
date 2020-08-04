package com.apetrov.kotlinrestapp.model

import java.math.BigDecimal
import java.time.LocalDate

data class Doctor(
        val name:String,
        val surname:String,
        val birthday:LocalDate,
        val salary:BigDecimal
) : BaseEntity() {
}