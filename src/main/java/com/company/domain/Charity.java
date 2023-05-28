package com.company.domain;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;


@Serdeable
@Data
@MappedEntity("charities")
public class Charity {

  @Id
  protected Long id;

  private final String name;

  private final String ein;

  private final String description;
}
