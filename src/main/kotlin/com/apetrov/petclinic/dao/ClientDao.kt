package com.apetrov.petclinic.dao

import com.apetrov.petclinic.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientDao : JpaRepository<Client, Long> {
    fun findByPhone(phone: String?): Optional<Client>
}