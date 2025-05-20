package org.joabe.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.joabe.entity.UserEventEntity;
import org.joabe.service.UserService;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class KafkaListener {

  private static final Logger logger = Logger.getLogger(KafkaListener.class);

  @Inject
  UserService userService;

  @Incoming("user-data")
  @Blocking
  public void processMessage(UserEventEntity userEventEntity) {
    logger.infof("new data received - %s", userEventEntity.getId());

    userService.process(userEventEntity);
  }
}
