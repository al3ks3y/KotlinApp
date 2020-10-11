package com.apetrov.petclinic.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter


@Configuration
class AppConfig {
    @Configuration
    class RequestLoggingFilterConfig {
        @Bean
        fun logFilter(): CommonsRequestLoggingFilter {
            val filter = CommonsRequestLoggingFilter()
            filter.setIncludeQueryString(true)
            filter.setIncludePayload(true)
            filter.setMaxPayloadLength(10000)
            filter.setIncludeHeaders(true)
            filter.setAfterMessagePrefix("REQUEST DATA : ")
            return filter
        }
    }
}
