package com.apetrov.petclinic.rest.indto

import javax.validation.constraints.NotNull


data class ClientInDto(
        @NotNull
        var phoneNumber: String?,
        @NotNull
        var name: String?,
        @NotNull
        var surname: String?,
        @NotNull
        var password: String?) {
}