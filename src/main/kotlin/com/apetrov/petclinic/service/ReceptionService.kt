package com.apetrov.petclinic.service

import com.apetrov.petclinic.dao.ClientDao
import com.apetrov.petclinic.dao.DoctorDao
import com.apetrov.petclinic.dao.ReceptionDao
import com.apetrov.petclinic.exceptions.UserNotFoundException
import com.apetrov.petclinic.rest.outdto.DoctorOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionDayOutDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import lombok.RequiredArgsConstructor
import org.joda.time.LocalDate
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.lang.NullPointerException
import javax.transaction.Transactional
@Service
@RequiredArgsConstructor
class ReceptionService (val doctorDao: DoctorDao, val receptionDao: ReceptionDao, val clientDao: ClientDao){
    @Transactional
    fun getDoctorsReceptions(doctorId:Long):List<ReceptionDayOutDto>{
        return getReceptionsByDoc(doctorId)
    }

    @Transactional
    fun getDoctorsReceptionsByDate(date: LocalDate, doctorId: Long): ReceptionDayOutDto? {
        return getReceptionsByDoc(doctorId).filter { it.date.equals(date) }.get(0)
    }

    @Transactional
    fun setOcupiedByRandomClient(receptionId: Long) {
        val reception = receptionDao.findById(receptionId).orElseThrow({ NullPointerException("Запись с данным ID не найдена") })
        val client = clientDao.findAll().stream().findAny().get()
        reception.client = client
        receptionDao.save(reception)
    }

    @Transactional
    fun getReceptionById(receptionId: Long): ReceptionOutDto {
        val reception = receptionDao.findById(receptionId).orElseThrow({ NullPointerException("Запись с данным ID не найдена") })
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val authenticatedClient = clientDao.findByPhone(authentication.getName()).orElse(null)
        return ReceptionOutDto(reception, authenticatedClient)
    }

    private fun getReceptionsByDoc(doctorId: Long): List<ReceptionDayOutDto> {
        val doctorOptional = doctorDao.findById(doctorId)
        val doctor = doctorOptional.orElseThrow({ NullPointerException("Доктор с данным ID не найден") })
        val receptionDayOutDtos = hashMapOf<LocalDate, ReceptionDayOutDto>()
        receptionDao.findByDoctorOrderById(doctor).forEach {
            val receptionDayOutDto = receptionDayOutDtos.get(it.beginTime.toLocalDate())
            if (receptionDayOutDto != null) {
                receptionDayOutDto.receptions.add(ReceptionOutDto(it))
            } else {
                receptionDayOutDtos.put(it.beginTime.toLocalDate(),
                        ReceptionDayOutDto(DoctorOutDto(doctor), it.beginTime.toLocalDate(), mutableListOf(ReceptionOutDto(it))))

            }
        }
        return receptionDayOutDtos.values.toList()
    }
}