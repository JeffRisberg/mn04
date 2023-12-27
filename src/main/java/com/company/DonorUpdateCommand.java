package com.company;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
@Serdeable
public class DonorUpdateCommand {
  @NotNull
  private final Long id;

  private final String firstName;
  private final String lastName;
  private final String address;
}
