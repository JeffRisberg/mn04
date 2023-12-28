package com.company;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Serdeable
public class DonorUpdateCommand {

  @NotNull
  private final Long id;

  private final String firstName;
  private final String lastName;
  private final String address;
}
