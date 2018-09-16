package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.service.AccountService
import br.com.camunda.example.exception.handler.NotFoundException
import br.com.camunda.example.exception.handler.error.ResourceValue
import br.com.camunda.example.repository.AccountRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl constructor(
    private val customerRepository: AccountRepository,
    private val accountRepository: AccountRepository
) : AccountService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun findById(id: String): Account {
        return getAccount(id)
    }

    override fun save(account: Account): Account {
        val result = accountRepository.save(account)

        log.debug("Account saved in database with values [{}]", result)
        return result
    }

    override fun update(account: Account): Account {
        log.debug("Account will be updated in database with values [{}]", account)

        val result = accountRepository.save(account)

        log.debug("Account updated in database with id {}", account.id)

        return result
    }

    override fun delete(id: String) {
        val account = getAccount(id)

        accountRepository.delete(id)

        log.debug("Account delete in database with id {}", account.id)
    }

    private fun getAccount(id: String): Account {
        return Optional.ofNullable(accountRepository.findOne(id))
            .orElseThrow {
                NotFoundException(ResourceValue(Account::class.java, id))
            }
    }

    private fun getCustomer(id: String): Account {
        return Optional.ofNullable(customerRepository.findOne(id))
            .orElseThrow {
                NotFoundException(ResourceValue(Account::class.java, id))
            }
    }

}