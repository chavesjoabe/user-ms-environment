package org.joabe;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserIdsProducer {
  @Channel("user-ids")
  private Emitter<String> emitter;

  public void sendMessage(String message) {
    emitter.send(message);
  }
}
