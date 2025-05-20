package org.joabe.service;

import org.joabe.entity.UserEventEntity;
import org.joabe.s3.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

  S3Service s3Service;
  UserService service;

  @BeforeEach
  public void setup() {
    s3Service = Mockito.mock(S3Service.class);

    service = new UserService();
    service.s3Service = s3Service;

  }

  @Test
  void ShouldProcessData() {
    UserEventEntity userEventEntity = new UserEventEntity("TEST", "", "", "");

    Mockito.doNothing().when(s3Service).uploadCsvFile(Mockito.any());

    service.process(userEventEntity);

    Mockito.verify(s3Service, Mockito.times(1)).uploadCsvFile(Mockito.any());
  }

}
