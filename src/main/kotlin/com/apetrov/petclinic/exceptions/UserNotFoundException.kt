package com.apetrov.petclinic.exceptions


import javax.xml.ws.WebServiceException

class UserNotFoundException : WebServiceException("Пользователь не найден") {
}