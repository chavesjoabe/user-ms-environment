package org.joabe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.joabe.entity.UserEventEntity;
import org.junit.jupiter.api.Test;

public class CsvUtilsTest {

  @Test
  void ShouldCreateCsvLine() {

    UserEventEntity userEventEntity = new UserEventEntity(
        "123",
        "TEST NAME",
        "12312312312",
        "available");

    String result = CsvUtils.createCsvLine(userEventEntity);

    String expected = "123,TEST NAME,12312312312,available\n";

    assertEquals(expected, result);
  }

  @Test
  void ShouldCreateCsvFile() {
    UserEventEntity userEventEntity = new UserEventEntity(
        "123",
        "TEST NAME",
        "12312312312",
        "available");

    List<UserEventEntity> users = List.of(userEventEntity);

    byte[] csvData = CsvUtils.generateCsv(users);

    String expected = "id,name,document,situation\n" +
        "123,TEST NAME,12312312312,available\n";

    String result = new String(csvData, StandardCharsets.UTF_8);

    assertEquals(expected, result);
  }

  @Test
  void ShouldCreateCsvFileWithABiggerList() {
    UserEventEntity userEventEntity = new UserEventEntity(
        "123",
        "TEST NAME",
        "12312312312",
        "available");

    UserEventEntity userEventEntity2 = new UserEventEntity(
        "1234",
        "TEST NAME 2",
        "32132132132",
        "available");

    UserEventEntity userEventEntity3 = new UserEventEntity(
        "12345",
        "TEST NAME 3",
        "12332112312",
        "available");

    List<UserEventEntity> users = List.of(
        userEventEntity,
        userEventEntity2,
        userEventEntity3);

    byte[] csvData = CsvUtils.generateCsv(users);

    String expected = "id,name,document,situation\n" +
        "123,TEST NAME,12312312312,available\n" +
        "1234,TEST NAME 2,32132132132,available\n" +
        "12345,TEST NAME 3,12332112312,available\n";

    String result = new String(csvData, StandardCharsets.UTF_8);

    assertEquals(expected, result);
  }
}
