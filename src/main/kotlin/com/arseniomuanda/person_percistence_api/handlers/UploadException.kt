package com.arseniomuanda.person_percistence_api.handlers

import java.lang.RuntimeException

class UploadException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
}