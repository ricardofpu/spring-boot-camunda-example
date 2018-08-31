package br.com.camunda.example.infrastructure

import javax.validation.ConstraintViolationException
import javax.validation.Validation
import javax.validation.Validator

object Validator {

    val validator: Validator by lazy {
        Validation.buildDefaultValidatorFactory().validator
    }

    fun <T> validate(instance: T) {
        val validate = validator.validate(instance)

        if (!validate.isEmpty()) {
            throw ConstraintViolationException(validate)
        }
    }
}