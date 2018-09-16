package br.com.camunda.example.exception.handler.error

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResourceValueResponse(
    private val resource: String,
    private val value: String
) {

    constructor(resourceValue: ResourceValue) : this(resourceValue.resource, resourceValue.value)

}