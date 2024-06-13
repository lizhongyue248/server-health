package wiki.zyue.agent.controller

import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class ClientController(
    val rSocketRequester: RSocketRequester
) {

    @GetMapping("/current")
    fun current(): Mono<String> {
        return rSocketRequester
            .route("health")
            .data("test")
            .retrieveMono(String::class.java)
    }
}