package com.arseniomuanda.person_percistence_api.handlers

import com.arseniomuanda.person_percistence_api.dtos.CreateUser

class InvalidRequest: RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
}