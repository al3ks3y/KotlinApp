package com.apetrov.petclinic.service

import com.apetrov.petclinic.dao.ClientDao
import com.apetrov.petclinic.exceptions.UserNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientDetailsService(val clientDao: ClientDao) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = clientDao.findByPhone(username).orElseThrow { UserNotFoundException() }
        return User(user.phone, user.encodedPassword, getAuthorities(user.authorities.map { it.name }))
    }

    private fun getAuthorities(roles: List<String>): List<GrantedAuthority>? {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority(role))
        }
        return authorities
    }
}