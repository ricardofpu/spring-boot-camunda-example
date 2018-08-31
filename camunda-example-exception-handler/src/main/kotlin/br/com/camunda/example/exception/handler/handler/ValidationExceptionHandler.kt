package br.com.camunda.example.exception.handler.handler

import br.com.camunda.example.exception.handler.error.ExceptionResponse
import br.com.camunda.example.exception.handler.utils.ValidationUtil
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.ConstraintViolationException

@ControllerAdvice
@ResponseBody
open class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun processValidationError(ex: MethodArgumentNotValidException): ExceptionResponse {
        val bindingResult = ex.bindingResult
        val response = ExceptionResponse()
        response.code = HttpStatus.BAD_REQUEST.value()
        response.fields = ValidationUtil.fromBindingsResult(bindingResult.fieldErrors)
        return response
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun processConstraintValidationError(ex: ConstraintViolationException): ExceptionResponse {
        val response = ExceptionResponse()
        response.code = HttpStatus.BAD_REQUEST.value()
        response.fields = ValidationUtil.fromBindingsResult(ex.constraintViolations)
        return response
    }
}