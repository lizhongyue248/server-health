package wiki.zyue.lever.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono


@Controller
class HealthMessage {

    @MessageMapping("health")
    fun message(input: Mono<String>): Mono<String> {
        return input.doOnNext { msg: String -> println("Request is:$msg,Request!") }
            .map { msg: String -> "$msg,Response!" }
    }

}