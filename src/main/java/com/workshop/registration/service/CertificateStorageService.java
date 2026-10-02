package com.workshop.registration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Uploads course-completion certificates to cloud storage (AWS S3).
 *
 * <p>Only reports whether credentials are configured - the upload itself is out of scope for the workshop.</p>
 */
@Service
public class CertificateStorageService {

    private final String bucket;
    private final String accessKeyId;
    private final String secretAccessKey;

    public CertificateStorageService(
            @Value("${app.certificates.bucket:}") String bucket,
            @Value("${app.certificates.aws-access-key-id:}") String accessKeyId,
            @Value("${app.certificates.aws-secret-access-key:}") String secretAccessKey) {
        this.bucket = bucket;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
    }

    public boolean isConfigured() {
        return !accessKeyId.isBlank() && !secretAccessKey.isBlank();
    }

    public String getBucket() {
        return bucket;
    }
}
