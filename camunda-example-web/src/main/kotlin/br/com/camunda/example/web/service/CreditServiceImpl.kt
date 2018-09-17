package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.Credit
import br.com.camunda.example.domain.service.CreditService
import br.com.camunda.example.repository.CreditRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class CreditServiceImpl constructor(
    private val repository: CreditRepository
) : CreditService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun findByTransactionId(transactionId: String): Credit {
        val result = repository.findByTransactionId(transactionId)

        log.debug("Credit founded in database with values [{}]", result)
        return result
    }

    override fun save(credit: Credit): Credit {
        val result = repository.save(credit)

        log.debug("Credit saved in database with values [{}]", result)
        return result
    }

}