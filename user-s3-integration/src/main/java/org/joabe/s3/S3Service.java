package org.joabe.s3;

import java.time.LocalTime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@ApplicationScoped
public class S3Service {

  @Inject
  S3Client s3Client;

  public void uploadCsvFile(byte[] csvFile) {
    String filename = String.format("%s.csv", LocalTime.now().toString());

    PutObjectRequest objectRequest = PutObjectRequest
        .builder()
        .bucket("user-data-files")
        .key(filename)
        .contentType("text/csv")
        .build();

    s3Client.putObject(
        objectRequest,
        software.amazon.awssdk.core.sync.RequestBody.fromBytes(csvFile));
  }
}
