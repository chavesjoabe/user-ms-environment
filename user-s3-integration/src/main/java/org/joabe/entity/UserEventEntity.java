package org.joabe.entity;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecordBase;

public class UserEventEntity extends SpecificRecordBase {
  private String id;
  private String name;
  private String document;
  private String situation;

  public UserEventEntity(String id, String name, String document, String situation) {
    this.id = id;
    this.name = name;
    this.document = document;
    this.situation = situation;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getSituation() {
    return situation;
  }

  public void setSituation(String situation) {
    this.situation = situation;
  }

  @Override
  public Object get(int field) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  @Override
  public Schema getSchema() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSchema'");
  }

  @Override
  public void put(int field, Object value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'put'");
  }

}
