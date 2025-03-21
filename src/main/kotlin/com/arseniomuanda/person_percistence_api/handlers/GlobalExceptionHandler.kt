package com.arseniomuanda.person_percistence_api.handlers

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintValidationException(
        e: ConstraintViolationException): ResponseEntity<in Map<String, Any>?> {
        val errorMessages = e.constraintViolations.associate { violation ->
            violation.propertyPath.toString() to violation.message
        }
        return ResponseEntity(errorMessages, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidRequest::class)
    fun handleAlreadyExistsException(e: InvalidRequest): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)


    @ExceptionHandler(AlreadyExistisException::class)
    fun handleAlreadyExistsException(e: AlreadyExistisException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)

    @ExceptionHandler(InternalException::class)
    fun handleInternalException(e: InternalException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)

    @ExceptionHandler(InvalidRequestException::class)
    fun handleInvalidRequestException(e: InvalidRequestException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)

    @ExceptionHandler(UploadException::class)
    fun handleUploadException(e: UploadException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
}