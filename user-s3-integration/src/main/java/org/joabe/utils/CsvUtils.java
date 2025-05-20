package org.joabe.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.joabe.entity.UserEventEntity;

public class CsvUtils {

  public static String createCsvLine(UserEventEntity user) {
    return String.format("%s,%s,%s,%s\n",
        user.getId(),
        user.getName(),
        user.getDocument(),
        user.getSituation());
  }

  public static byte[] generateCsv(List<UserEventEntity> userList) {
    StringBuilder stringBuilder = new StringBuilder("id,name,document,situation\n");
    userList.stream().forEach(user -> {
      stringBuilder.append(createCsvLine(user));
    });

    return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
  }
}
