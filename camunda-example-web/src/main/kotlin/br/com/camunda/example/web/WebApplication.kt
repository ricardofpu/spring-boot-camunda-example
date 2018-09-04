package br.com.camunda.example.web

import br.com.camunda.example.repository.config.RepositoryConfiguration
import org.apache.logging.log4j.LogManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.net.InetAddress

@SpringBootApplication
@Configuration
@Import(RepositoryConfiguration::class)
@ComponentScan(basePackages = ["br.com.camunda.example"])
@EntityScan(basePackages = ["br.com.camunda.example.domain"])
@EnableAutoConfiguration
open class ApplicationConfig

private val logger = LogManager.getLogger("br.com.camunda.example.web.WebApplication")

fun main(args: Array<String>) {
    val app = SpringApplication.run(ApplicationConfig::class.java, *args)

    val applicationName = app.environment.getProperty("spring.application.name")
    val contextPath = app.environment.getProperty("server.contextPath")
    val port = app.environment.getProperty("server.port")
    val hostAddress = InetAddress.getLocalHost().hostAddress

    logger.info(
        """|
           |------------------------------------------------------------
           |Application '$applicationName' is running! Access URLs:
           |   Local:      http://127.0.0.1:$port$contextPath
           |   External:   http://$hostAddress:$port$contextPath
           |------------------------------------------------------------""".trimMargin()
    )

}