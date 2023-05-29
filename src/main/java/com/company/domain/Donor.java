package com.company.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable
@Data
@MappedEntity(value = "donors")
public class Donor {

  @Id
  @GeneratedValue(GeneratedValue.Type.AUTO)
  private Long id;

  @MappedProperty(value = "first_name")
  private final String firstName;

  @MappedProperty(value = "last_name")
  private final String lastName;

  private final String address;
}
