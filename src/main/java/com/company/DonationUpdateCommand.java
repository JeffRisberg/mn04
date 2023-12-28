package com.company;

import io.micronaut.serde.annotation.Serdeable;

import java.sql.Timestamp;
import java.util.Date;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
  private Double amount;

  @NotNull
  private Timestamp dateCreated;

  @NotNull
  private Timestamp lastUpdated;
}
