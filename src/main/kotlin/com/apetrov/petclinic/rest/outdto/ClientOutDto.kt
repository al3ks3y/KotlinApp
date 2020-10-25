package com.apetrov.petclinic.rest.outdto

import com.apetrov.petclinic.model.Client

data class ClientOutDto(
        var name: String?,
        var surname: String?,
        var phoneNumber: String?
) {
    constructor(client: Client?) : this(
            client?.name,
            client?.surname,
            client?.phone
    )
}
