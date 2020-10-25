package com.apetrov.petclinic.rest

import com.apetrov.petclinic.model.Client
import com.apetrov.petclinic.rest.indto.ClientInDto
import com.apetrov.petclinic.rest.outdto.ReceptionOutDto
import com.apetrov.petclinic.service.AccountService
import com.apetrov.petclinic.util.ACCOUNT_API_PREFIX
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import java.security.Principal
import javax.validation.Valid


@RestController
@RequestMapping(ACCOUNT_API_PREFIX)
class AccountController(val accountService: AccountService) {
    @PostMapping("/register")
    fun registerClient(@RequestBody @Valid clientInDto: ClientInDto) {
        accountService.registerNewClient(clientInDto)
    }

    @ApiOperation("Получить записи авторизованного пользователя")
    @GetMapping("/receptions")
    fun getMyReceptions(): List<ReceptionOutDto> {
        return accountService.getReceptions()
    }

    @ApiOperation("Забронировать запись авторизованным пользователем")
    @PutMapping("/receptions/{receptionId}")
    fun bookReception(@PathVariable("receptionId") receptionId: Long) {
        accountService.bookReception(receptionId)
    }
}