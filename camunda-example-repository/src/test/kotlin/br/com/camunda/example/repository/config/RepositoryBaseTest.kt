package br.com.camunda.example.repository.config

import br.com.camunda.example.domain.dummyAccount
import br.com.camunda.example.domain.dummyCustomer
import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.repository.AccountRepository
import br.com.camunda.example.repository.CreditRepository
import br.com.camunda.example.repository.CustomerRepository
import br.com.camunda.example.repository.DebitRepository
import br.com.camunda.example.repository.TransferenceRepository
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@ContextConfiguration
@SpringBootTest(classes = [RepositoryTestConfig::class])
@Sql(
    scripts = ["classpath:sqlScripts/clear_tables.sql"],
    config = SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED),
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
abstract class RepositoryBaseTest {

    @Autowired
    protected lateinit var customerRepository: CustomerRepository

    @Autowired
    protected lateinit var accountRepository: AccountRepository

    @Autowired
    protected lateinit var debitRepository: DebitRepository

    @Autowired
    protected lateinit var creditRepository: CreditRepository

    @Autowired
    protected lateinit var transferenceRepository: TransferenceRepository

    protected fun createCustomer(customer: Customer = dummyCustomer()): Customer {
        val saved = customerRepository.save(customer)
        assertNotNull(saved)

        return saved
    }

    protected fun creatAccount(customer: Customer = dummyCustomer()): Account {
        val savedCustomer = createCustomer(customer)
        val account = dummyAccount(customer = savedCustomer)

        val savedAccount = accountRepository.save(account)
        assertNotNull(savedAccount)

        return savedAccount
    }

}