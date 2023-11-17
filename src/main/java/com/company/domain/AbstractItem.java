package com.company.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractItem implements Serializable {
  @Id
  @Column(name = "id", unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  protected void init(long id) {
    this.setId(id);
  }

  protected void init(String id, EntityType type) {
    this.setId(CryptoUtils.generateEntityId(id, type));
  }

  @Column(name = "date_created", nullable = false)
  private Date dateCreated;

  @Column(name = "last_updated", nullable = true)
  private Date lastUpdated;

  /*
  @PrePersist
  protected void onCreate() {
    System.out.println("CHECK onCREATE");
    dateCreated = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    System.out.println("CHECK onUPDATE");
    lastUpdated = new Date();
  }
  */
}
