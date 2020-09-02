package com.apetrov.petclinic.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
class WebConfiguration : WebMvcConfigurerAdapter() {
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        val converter = MappingJackson2HttpMessageConverter()
        val objectMapper = ObjectMapper()

        //configure Joda serialization
        objectMapper.registerModule(JodaModule())
        objectMapper.configure(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        // Other options such as how to deal with nulls or identing...
        objectMapper.setSerializationInclusion(
                JsonInclude.Include.NON_NULL)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
        converter.objectMapper = objectMapper
        converters.add(converter)
        super.configureMessageConverters(converters)
    }
}