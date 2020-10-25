package com.apetrov.petclinic.exceptions

import javax.xml.ws.WebServiceException

class UserAlreadyExistsException : WebServiceException("Пользователь уже существует") {
}