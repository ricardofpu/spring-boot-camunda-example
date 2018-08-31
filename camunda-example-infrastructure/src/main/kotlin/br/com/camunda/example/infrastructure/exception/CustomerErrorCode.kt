package br.com.camunda.example.infrastructure.exception

import br.com.camunda.example.exception.handler.error.ErrorCode

private class CustomerError(code: String, key: String) : ErrorCode(code, key)

object CustomerErrorCode {

    val CUSTOMER_IS_NOT_ACTIVE: ErrorCode =
        CustomerError("CUSTOMER_IS_NOT_ACTIVE", "customer.is.not.active")

    val CHANGE_STATUS_NOT_ALLOWED: ErrorCode =
        CustomerError("CHANGE_STATUS_NOT_ALLOWED", "change.status.not.allowed")

    val STATUS_INVALID_TO_DELETE: ErrorCode =
        CustomerError("STATUS_INVALID_TO_DELETE", "status.invalid.to.delete")

}