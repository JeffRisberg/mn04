package com.company;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

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
  private LocalDateTime dateCreated;

  @NotNull
  private LocalDateTime lastUpdated;
}
