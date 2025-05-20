package org.joabe.kafka;

import org.joabe.entity.UserEventEntity;
import org.joabe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KafkaListenerTest {

  UserService userService;
  KafkaListener kafkaListener;

  @BeforeEach
  void setup() {
    userService = Mockito.mock(UserService.class);

    kafkaListener = new KafkaListener();

    kafkaListener.userService = userService;
  }

  @Test
  void ShouldProcessData() {
    UserEventEntity userEventEntity = new UserEventEntity("TEST", "", "", "");

    Mockito.doNothing().when(userService).process(userEventEntity);

    kafkaListener.processMessage(userEventEntity);

    Mockito.verify(userService, Mockito.times(1)).process(userEventEntity);
  }
}
