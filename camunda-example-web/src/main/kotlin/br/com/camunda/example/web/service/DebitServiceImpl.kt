package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.Debit
import br.com.camunda.example.domain.service.DebitService
import br.com.camunda.example.repository.DebitRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class DebitServiceImpl constructor(
    private val repository: DebitRepository
) : DebitService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun findByTransactionId(transactionId: String): Debit {
        val result = repository.findByTransactionId(transactionId)

        log.debug("Debit founded in database with values [{}]", result)
        return result
    }

    override fun save(debit: Debit): Debit {
        val result = repository.save(debit)

        log.debug("Debit saved in database with values [{}]", result)
        return result
    }

}