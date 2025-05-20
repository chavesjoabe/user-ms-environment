package org.joabe.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.joabe.entity.UserEventEntity;
import org.joabe.s3.S3Service;
import org.joabe.utils.CsvUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class);

  @Inject
  S3Service s3Service;

  public void process(UserEventEntity userEventEntity) {
    byte[] csvData = CsvUtils.generateCsv(List.of(userEventEntity));

    s3Service.uploadCsvFile(csvData);
    logger.infof("file uploaded with success");
  }
}
