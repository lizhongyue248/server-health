package wiki.zyue.lever.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono
import wiki.zyue.shared.modal.AgentInformation
import wiki.zyue.shared.modal.Route


@Controller
class HealthMessage {

    @MessageMapping(Route.REPORT)
    fun message(@Payload input: AgentInformation): Mono<Unit> {
        println(input)
        return Mono.empty();
    }

}