package com.apetrov.petclinic.exceptions

import javax.xml.ws.WebServiceException

class AlreadyOccupiedException : WebServiceException("Запись уже занята") {
}