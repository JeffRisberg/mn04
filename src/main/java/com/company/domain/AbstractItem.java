package com.company.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Serdeable
@Data
public class AbstractItem implements Serializable {

  @Id
  @GeneratedValue(GeneratedValue.Type.AUTO)
  private Long id;

  protected void init(long id) {
    this.setId(id);
  }

  protected void init(String id, EntityType type) {
    this.setId(CryptoUtils.generateEntityId(id, type));
  }

  @NotNull
  @MappedProperty(value = "date_created")
  private LocalDateTime dateCreated;

  @NotNull
  @MappedProperty(value = "last_updated")
  private LocalDateTime lastUpdated;
}
