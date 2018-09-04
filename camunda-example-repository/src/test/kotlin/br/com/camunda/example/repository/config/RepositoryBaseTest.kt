package br.com.camunda.example.repository.config

import br.com.camunda.example.repository.CustomerRepository
import br.com.camunda.example.repository.PaymentTransactionRepository
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.junit4.SpringRunner

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
    protected lateinit var paymentTransactionRepository: PaymentTransactionRepository

}