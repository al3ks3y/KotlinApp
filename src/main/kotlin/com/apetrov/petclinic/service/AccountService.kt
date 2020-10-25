package com.apetrov.petclinic.service

import com.apetrov.petclinic.dao.ClientDao
import com.apetrov.petclinic.dao.ReceptionDao
import com.apetrov.petclinic.exceptions.AlreadyOccupiedException
import com.apetrov.petclinic.exceptions.UserAlreadyExistsException
import com.apetrov.petclinic.exceptions.UserNotFoundException
import com.apetrov.petclinic.model.Client
import com.apetrov.petclinic.rest.indto.ClientInDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import javassist.NotFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class AccountService(val clientDao: ClientDao,
                     val passwordEncoder: BCryptPasswordEncoder,
                     val receptionDao: ReceptionDao) {
    fun registerNewClient(dto: ClientInDto) {
        if (phoneExists(dto.phoneNumber)) {
            throw UserAlreadyExistsException()
        }
        val client = Client(dto.name, dto.surname, dto.phoneNumber,
                passwordEncoder.encode(dto.password))
        clientDao.save(client)
    }

    fun getReceptions(): List<ReceptionOutDto> {
        val currentPrincipalName: String = getCurrentPrincipal()
        val client = clientDao.findByPhone(currentPrincipalName).orElseThrow { UserNotFoundException() }
        return receptionDao.findByClientOrderById(client).map { ReceptionOutDto(it, client) }
    }

    fun bookReception(receptionId: Long) {
        val currentPrincipalName: String = getCurrentPrincipal()
        val reception = receptionDao.findById(receptionId).orElseThrow { NotFoundException("Запись не найдена") }
        if (reception.client != null) {
            throw AlreadyOccupiedException()
        }
        val client = clientDao.findByPhone(currentPrincipalName).orElseThrow { UserNotFoundException() }
        reception.client = client
        receptionDao.save(reception)
    }

    private fun getCurrentPrincipal(): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        if (!authentication.isAuthenticated || authentication.authorities.contains(SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
            throw AccessDeniedException("Доступ запрещен")
        }
        return authentication.getName()
    }

    private fun phoneExists(phone: String?): Boolean {
        return clientDao.findByPhone(phone).isPresent
    }
}