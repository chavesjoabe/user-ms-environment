package org.joabe;

import java.util.Random;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "user-ids-producer", mixinStandardHelpOptions = true)
public class ProducerUserIdsCommand implements Runnable {

  @Option(names = { "-c",
      "--count" }, defaultValue = "1", description = "Number of messages to be sent", required = true)
  int count;

  @Inject
  UserIdsProducer producer;

  @Override
  public void run() {
    Random random = new Random();
    int minValue = 1;
    int userIdsInDCount = 50;

    for (int i = 0; i < count; i++) {
      String randomId = String
          .format("%s", random.nextInt((userIdsInDCount - minValue) + 1) + 1);

      producer.sendMessage(randomId);
      System.out.println("Message sent with success to id - " + randomId);
    }
  }
}
