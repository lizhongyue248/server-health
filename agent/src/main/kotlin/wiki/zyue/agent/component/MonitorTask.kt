package wiki.zyue.agent.component

import org.slf4j.LoggerFactory
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import wiki.zyue.shared.modal.*
import wiki.zyue.shared.service.MonitorService


@Component
class MonitorTask(
    private val client: RSocketRequester,
    private val monitorService: MonitorService
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(cron = "*/2 * * * * *")
    fun report() {
        val information = monitorService.statistics()
        log.info(information.toString())
        client.route(Route.REPORT)
            .data(information)
            .send()
            .subscribe()
    }


}