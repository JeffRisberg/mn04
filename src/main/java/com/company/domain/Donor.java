package com.company.domain;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable
@Data
@MappedEntity("donors")
public class Donor {

  @Id
  protected Long id;

  @MappedProperty("first_name")
  private final String firstName;

  @MappedProperty("last_name")
  private final String lastName;

  private final String address;
}
