package com.apetrov.petclinic.model

import lombok.ToString
import lombok.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.io.Serializable
import javax.persistence.*

@Value
@Entity
@Table
@ToString
class Client(
        var name: String?,
        var surname: String?,
        var phone: String?,
        var encodedPassword: String,
        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "clientId")
        var receptions: MutableList<Reception> = arrayListOf(),
        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "clientId")
        var authorities: MutableList<Role> =
                arrayListOf(Role("ROLE_USER"))
) : BaseEntity(), Serializable