package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.model.Credit
import br.com.camunda.example.domain.model.Debit
import br.com.camunda.example.domain.service.AccountService
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.exception.handler.BusinessException
import br.com.camunda.example.exception.handler.NotFoundException
import br.com.camunda.example.exception.handler.error.ResourceValue
import br.com.camunda.example.infrastructure.exception.CamundaExampleErrorCode
import br.com.camunda.example.repository.AccountRepository
import br.com.camunda.example.repository.CreditRepository
import br.com.camunda.example.repository.DebitRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
open class AccountServiceImpl constructor(
    private val customerRepository: AccountRepository,
    private val accountRepository: AccountRepository,
    private val creditRepository: CreditRepository,
    private val debitRepository: DebitRepository,
    private val customerService: CustomerService
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

    //TODO: Improve a lock to balance and avoid concurrency
    //TODO: Validate credit currency with account currency
    override fun saveCredit(credit: Credit): Credit {
        log.debug("Credit will be saved in database with values [{}]", credit)

        customerService.validateStatus(credit.account.customer.id)//TODO: handle exception and throw BusinessException

        val result = creditRepository.save(credit)

        this.updateBalanceByAccountId(credit)

        log.debug("Credit saved in database with id {}", result.id)

        return result
    }

    //TODO: Improve a lock to balance and avoid concurrency
    //TODO: Validate debit currency with account currency
    override fun saveDebit(debit: Debit): Debit {
        log.debug("Debit will be saved in database with values [{}]", debit)

        customerService.validateStatus(debit.account.customer.id)//TODO: handle exception and throw BusinessException

        val result = debitRepository.save(debit)

        this.removeBalanceByAccountId(debit)

        log.debug("Credit saved in database with id {}", result.id)

        return result
    }

    private fun removeBalanceByAccountId(debit: Debit) {
        val debitPrice = debit.getPrice()
        val account = getAccount(debit.account.id)
        val balance = account.getBalance()
        val newBalance = (balance.minus(debitPrice))

        if (newBalance < BigDecimal.ZERO) {
            throw BusinessException(CamundaExampleErrorCode.INSUFFICIENT_BALANCE_EXCEPTION)
        }

        accountRepository.updateBalanceAmountByAccountId(account.id, convertBalanceToScaleTwo(newBalance).toLong())
    }


    private fun updateBalanceByAccountId(credit: Credit) {
        val creditPrice = credit.getValue()
        val account = getAccount(credit.account.id)
        val balance = account.getBalance()
        val newBalance: BigDecimal = convertBalanceToScaleTwo(balance.add(creditPrice))

        accountRepository.updateBalanceAmountByAccountId(account.id, newBalance.toLong())
    }

    private fun convertBalanceToScaleTwo(newBalance: BigDecimal): BigDecimal {
        return newBalance.multiply(BigDecimal.valueOf(100))
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
