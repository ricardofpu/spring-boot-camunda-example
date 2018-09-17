package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.Transference

interface Workflow {

    fun start(transference: Transference)
}