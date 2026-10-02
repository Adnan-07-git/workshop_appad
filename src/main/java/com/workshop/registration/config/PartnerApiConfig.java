package com.workshop.registration.config;

import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Settings for calling the partner university's certificate-verification API.
 */
@Configuration
public class PartnerApiConfig {

    /**
     * TLS context used for the HTTPS connection to the partner API.
     */
    @Bean
    public SSLContext partnerSslContext() throws NoSuchAlgorithmException {
        return SSLContext.getInstance("TLSv1");
    }
}
