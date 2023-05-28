package com.company.domain;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Serdeable
@Entity
@Table(name = "donors")
@Data
public class Donor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotNull
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotNull
  @Column(name = "last_name", nullable = false)
  private String lastName;

  private String address;
}
