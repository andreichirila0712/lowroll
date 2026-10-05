package dev.andrei.chirila.lowroll.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.Platform;

@Configuration
public class ClamAvConfig {
    @Value("${clamav.host}")
    private String host;
    @Value("${clamav.port}")
    private int port;

    @Bean
    public ClamavClient clamavClient() {
        return new ClamavClient(host, port, Platform.JVM_PLATFORM);
    }
}
