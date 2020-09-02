package com.apetrov.petclinic.service

import com.apetrov.petclinic.dao.DoctorDao
import com.apetrov.petclinic.dao.ReceptionDao
import com.apetrov.petclinic.rest.outdto.DoctorOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import org.joda.time.LocalDate
import org.springframework.stereotype.Service
import java.lang.NullPointerException
import javax.transaction.Transactional

@Service
class DoctorService (val doctorDao: DoctorDao, val receptionDao: ReceptionDao){
    @Transactional
    fun getAll():List<DoctorOutDto>{
       return doctorDao.findAll().map { DoctorOutDto(it) }
    }
    @Transactional
    fun getDoctorsReceptions(doctorId:Long):List<ReceptionDayOutDto>{
        return getReceptionsByDoc(doctorId)
    }
    @Transactional
    fun getDoctorsReceptionsByDate(date:LocalDate, doctorId: Long):ReceptionDayOutDto?{
        return getReceptionsByDoc(doctorId).filter { it.date.equals(date) }.get(0)
    }
    private fun getReceptionsByDoc(doctorId: Long):List<ReceptionDayOutDto>{
        val doctorOptional=doctorDao.findById(doctorId)
        val doctor=doctorOptional.orElseThrow({NullPointerException("Доктор с данным ID не найден")})
        var receptionDayOutDtos= hashMapOf<LocalDate,ReceptionDayOutDto>()
        receptionDao.findByDoctor(doctor).forEach {
            val receptionDayOutDto = receptionDayOutDtos.get(it.beginTime.toLocalDate())
            if(receptionDayOutDto!=null){
                receptionDayOutDto.receptions.add(ReceptionOutDto(it))
            }
            else{
                receptionDayOutDtos.put(it.beginTime.toLocalDate(),
                        ReceptionDayOutDto(DoctorOutDto(doctor), it.beginTime.toLocalDate(), mutableListOf(ReceptionOutDto(it))))

            }
        }
        return receptionDayOutDtos.values.toList()
    }

}