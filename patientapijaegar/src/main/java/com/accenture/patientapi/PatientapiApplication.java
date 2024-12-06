package com.accenture.patientapi;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientapiApplication.class, args);
    }

    @Bean
    public static JaegerTracer getTracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        Configuration config = new Configuration("patientservice").withSampler(samplerConfig).withReporter(reporterConfig);


        return config.getTracer();

    }

    /*@Bean
    public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
    }*/
}
