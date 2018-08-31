package br.com.camunda.example.exception.handler.error

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
open class ExceptionResponse {

    var code: Int? = null
    var errorMessage: String? = null
    var fields: Map<String, List<String>>? = null

    constructor() {}

    constructor(errorMessage: String) {
        this.errorMessage = errorMessage
    }

    constructor(code: Int, errorMessage: String?) {
        this.code = code
        this.errorMessage = errorMessage
    }

    constructor(code: Int, fields: Map<String, List<String>>) {
        this.code = code
        this.fields = fields
    }

    constructor(code: Int, errorMessage: String, fields: Map<String, List<String>>) {
        this.code = code
        this.errorMessage = errorMessage
        this.fields = fields
    }

    constructor(fields: Map<String, List<String>>) {
        this.fields = fields
    }

    constructor(errorCode: ErrorCode?) {
        this.errorMessage = errorCode?.key
    }

}