package wiki.zyue.agent.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import wiki.zyue.shared.service.MonitorService
import wiki.zyue.shared.service.MonitorServiceImpl

@Configuration
@EnableScheduling
class ApplicationConfiguration {

    @Bean
    fun monitorService(): MonitorService = MonitorServiceImpl()

}