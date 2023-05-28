package com.company.domain;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Serdeable
@Entity
@Table(name = "charities")
@Data
public class Charity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotNull
  private String name;

  @NotNull
  private String ein;

  private String description;
}
