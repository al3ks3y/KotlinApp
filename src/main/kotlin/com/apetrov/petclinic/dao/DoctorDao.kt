package com.apetrov.petclinic.dao

import com.apetrov.petclinic.model.Branch
import com.apetrov.petclinic.model.Doctor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DoctorDao : JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE upper(d.name) LIKE CONCAT('%', UPPER(:search) ,'%') OR " +
            "upper(d.surname) LIKE CONCAT('%', UPPER(:search) ,'%')")
    fun findBySearchStr(@Param("search") search: String): List<Doctor>
    fun findByBranch(branch: Branch):List<Doctor>
}