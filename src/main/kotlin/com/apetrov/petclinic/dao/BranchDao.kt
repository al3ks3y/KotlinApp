package com.apetrov.petclinic.dao

import com.apetrov.petclinic.model.Branch
import org.springframework.data.jpa.repository.JpaRepository

interface BranchDao :JpaRepository<Branch, Long> {
}