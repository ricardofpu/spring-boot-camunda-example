package br.com.camunda.example.web.config

import br.com.camunda.example.web.ApplicationConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ApplicationConfig::class)
open class ApplicationConfigTest