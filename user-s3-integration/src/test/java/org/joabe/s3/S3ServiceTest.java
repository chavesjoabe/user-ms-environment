package org.joabe.s3;

import java.util.List;

import org.joabe.entity.UserEventEntity;
import org.joabe.utils.CsvUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3ServiceTest {

  S3Client s3Client;
  S3Service s3Service;

  @BeforeEach
  void setup() {
    s3Client = Mockito.mock(S3Client.class);
    s3Service = new S3Service();

    s3Service.s3Client = s3Client;
  }

  @Test
  void ShouldUploadFileToS3() {

    UserEventEntity user = new UserEventEntity(
        "123",
        "TEST NAME",
        "12312312312",
        "available");

    byte[] csvFile = CsvUtils.generateCsv(List.of(user));

    s3Service.uploadCsvFile(csvFile);

    Mockito
        .verify(s3Client, Mockito.times(1))
        .putObject(Mockito.any(PutObjectRequest.class), Mockito.any(RequestBody.class));
  }
}
