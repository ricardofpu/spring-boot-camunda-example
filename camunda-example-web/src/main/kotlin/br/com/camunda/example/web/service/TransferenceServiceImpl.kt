package br.com.camunda.example.web.service

import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.model.Transference
import br.com.camunda.example.domain.service.TransferenceService
import br.com.camunda.example.exception.handler.BusinessException
import br.com.camunda.example.exception.handler.NotFoundException
import br.com.camunda.example.exception.handler.error.ResourceValue
import br.com.camunda.example.infrastructure.exception.CamundaExampleErrorCode
import br.com.camunda.example.repository.TransferenceRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransferenceServiceImpl constructor(
    private val transferenceRepository: TransferenceRepository
) : TransferenceService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun findById(id: String): Transference {
        return getTransference(id)
    }

    override fun save(transference: Transference): Transference {
        val result = transferenceRepository.save(transference)

        log.debug("Transference saved in database with values [{}]", result)
        return result
    }

    override fun updateStatusAndReason(id: String, status: PaymentStatus, reason: String?) {
        log.debug(
            "Transference will be updated in database with id {} with status {} and reason",
            id, status.name, reason
        )

        val updated = transferenceRepository.updateStatusAndReason(id = id, status = status, reason = reason)

        if (updated != 1) {
            throw BusinessException(CamundaExampleErrorCode.UPDATE_TRANSFERENCE_NOT_EXECUTED)
        }

        log.debug("Transference updated in database with id {}", id)
    }

    private fun getTransference(id: String): Transference {
        return Optional.ofNullable(transferenceRepository.findOne(id))
            .orElseThrow {
                NotFoundException(ResourceValue(Transference::class.java, id))
            }
    }

}