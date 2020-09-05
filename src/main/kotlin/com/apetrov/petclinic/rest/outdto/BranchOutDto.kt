package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.model.Branch
import io.swagger.annotations.ApiModel

@ApiModel("Информация об отделении")
data class BranchOutDto(
        val id:Long?,
        val name: String
){
    constructor(branch: Branch):this(
            branch.id!!,
            branch.name
    )
}