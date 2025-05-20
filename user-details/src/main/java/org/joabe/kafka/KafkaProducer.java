package org.joabe.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
import org.joabe.avro.UserData;
import org.joabe.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class KafkaProducer {

  private static final Logger logger = Logger.getLogger(KafkaProducer.class);

  @Inject
  @Channel("user-data")
  Emitter<UserData> emitter;

  public void sendMessage(User user) {
    UserData userData = UserData
        .newBuilder()
        .setId(Integer.toString(user.id))
        .setName(user.name)
        .setDocument(user.document)
        .setSituation(user.situation)
        .build();

    emitter.send(userData);

    logger.infof("Produce message in topic user-data with success");
  }
}
