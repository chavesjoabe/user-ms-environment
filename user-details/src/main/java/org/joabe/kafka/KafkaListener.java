package org.joabe.kafka;

import java.util.Optional;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.joabe.entity.User;
import org.joabe.service.UserService;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class KafkaListener {

  private static final Logger logger = Logger.getLogger(KafkaListener.class);

  @Inject
  UserService userService;

  @Inject
  KafkaProducer kafkaProducer;

  @Incoming("user-ids")
  @Blocking
  public void processMessage(String userId) {
    logger.infof("new data received - %s", userId);

    User user = processData(userId);

    kafkaProducer.sendMessage(user);
  }

  @Transactional
  public User processData(String userId) {
    Optional<User> user = userService.findById(userId);
    if (user.isEmpty()) {
      logger.errorf("User with id %s not found", userId);
      throw new RuntimeException("User not found");
    }

    return user.get();
  }
}
