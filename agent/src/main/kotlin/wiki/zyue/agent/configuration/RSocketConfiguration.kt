package wiki.zyue.agent.configuration

import io.rsocket.core.RSocketConnector
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.util.MimeTypeUtils
import reactor.util.retry.Retry
import java.time.Duration

@Configuration
class RSocketConfiguration {
    @Bean
    fun getRSocketRequester(rSocketStrategies: RSocketStrategies): RSocketRequester {
        return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
            .rsocketConnector { rSocketConnector: RSocketConnector ->
                rSocketConnector.reconnect(
                    Retry.fixedDelay(2, Duration.ofSeconds(2))
                )
            }
            .tcp("localhost", 8081)
    }
}