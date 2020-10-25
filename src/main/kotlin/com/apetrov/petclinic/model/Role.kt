package com.apetrov.petclinic.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class Role(val name: String) : BaseEntity() {

}