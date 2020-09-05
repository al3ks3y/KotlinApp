package com.apetrov.petclinic.service

import com.apetrov.petclinic.dao.BranchDao
import com.apetrov.petclinic.dao.DoctorDao
import com.apetrov.petclinic.dao.ReceptionDao
import com.apetrov.petclinic.model.Branch
import com.apetrov.petclinic.rest.outdto.BranchOutDto
import com.apetrov.petclinic.rest.outdto.DoctorOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import javassist.NotFoundException
import org.joda.time.LocalDate
import org.springframework.stereotype.Service
import java.lang.NullPointerException
import javax.transaction.Transactional

@Service
class DoctorService (val doctorDao: DoctorDao, val branchDao: BranchDao){
    @Transactional
    fun getAll():List<DoctorOutDto>{
       return doctorDao.findAll().map { DoctorOutDto(it) }
    }

    @Transactional
    fun findDoctorByName(search: String):List<DoctorOutDto>{
        return doctorDao.findBySearchStr(search)
                .map { DoctorOutDto(it) }
    }
    @Transactional
    fun findDoctorsByBranch(branchId:Long):List<DoctorOutDto>{
        val branch = branchDao.findById(branchId).orElseThrow { NotFoundException("Отделение не найдено") }
        return doctorDao.findByBranch(branch).map{ DoctorOutDto(it) }
    }
    @Transactional
    fun getAllBranches():List<BranchOutDto>{
        return branchDao.findAll().map { BranchOutDto(it) }
    }

}