package br.com.camunda.example.exception.handler.utils

import org.springframework.validation.FieldError
import javax.validation.ConstraintViolation

object ValidationUtil {

    fun fromBindingsResult(errors: List<FieldError>): Map<String, List<String>> {
        val fields = linkedMapOf<String, List<String>>()
        errors.forEach { field ->
            fields.computeIfAbsent(field.field) { arrayListOf(field.defaultMessage) }
        }
        return fields
    }

    fun fromBindingsResult(constraintViolations: Set<ConstraintViolation<*>>): Map<String, List<String>> {
        val fields = linkedMapOf<String, List<String>>()
        constraintViolations.forEach { violation ->
            fields.computeIfAbsent(violation.propertyPath.toString()) { arrayListOf(violation.message) }
        }
        return fields
    }
}