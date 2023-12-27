package com.company;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
@Serdeable
public class CharityUpdateCommand {
  @NotNull
  private final Long id;

  private final String name;
}
