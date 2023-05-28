package com.company;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Serdeable
public class DonationUpdateCommand {
  @NotNull
  private final Long id;


  @NotNull
  private final Long donor_id;

  @NotNull
  private final Long charity_id;

  @NotNull
  private double amount;
}
