package br.com.camunda.example.infrastructure.exception

import br.com.camunda.example.exception.handler.error.ErrorCode

private class Error(code: String, key: String) : ErrorCode(code, key)

object CamundaExampleErrorCode {

    val CUSTOMER_IS_NOT_ACTIVE: ErrorCode =
        Error("CUSTOMER_IS_NOT_ACTIVE", "customer.is.not.active")

    val CHANGE_STATUS_NOT_ALLOWED: ErrorCode =
        Error("CHANGE_STATUS_NOT_ALLOWED", "change.status.not.allowed")

    val STATUS_INVALID_TO_DELETE: ErrorCode =
        Error("STATUS_INVALID_TO_DELETE", "status.invalid.to.delete")

    val INSUFFICIENT_BALANCE_EXCEPTION: ErrorCode =
        Error("INSUFFICIENT_BALANCE_EXCEPTION", "insufficient.balance.exception")

    val UPDATE_TRANSFERENCE_NOT_EXECUTED: ErrorCode =
            Error("UPDATE_TRANSFERENCE_NOT_EXECUTED", "update.transference.not.executed")

}