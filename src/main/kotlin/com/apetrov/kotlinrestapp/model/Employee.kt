package com.apetrov.kotlinrestapp.model

import java.math.BigDecimal
import java.time.LocalDate

open class Employee (val name:String,
                     val surname:String,
                     val birthday: LocalDate,
                     val salary: BigDecimal):BaseEntity(){

}