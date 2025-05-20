package org.joabe.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {
  @Id
  @Column(name = "id")
  public int id;
  @Column(name = "name")
  public String name;
  @Column(name = "document")
  public String document;
  @Column(name = "situation")
  public String situation;

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", document=" + document + ", situation=" + situation + "]";
  }

}
