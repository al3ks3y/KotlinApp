package com.apetrov.petclinic.dao

import com.apetrov.petclinic.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientDao : JpaRepository<Client,Long>{
}