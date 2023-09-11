package com.company.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Serdeable
@Data
@MappedEntity(value = "charities")
public class Charity {

  @Id
  @GeneratedValue(GeneratedValue.Type.AUTO)
  private Long id;

  @NotNull
  @MappedProperty(value = "name")
  private final String name;

  @NotNull
  @MappedProperty(value = "ein")
  private final String ein;

  @MappedProperty(value = "description")
  private final String description;
}
