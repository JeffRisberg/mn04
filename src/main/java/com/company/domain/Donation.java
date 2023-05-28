package com.company.domain;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Serdeable
@Entity
@Table(name = "donations")
@Data
@EqualsAndHashCode(callSuper = true)
public class Donation extends AbstractItem {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "donor_id")
  private Donor donor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "charity_id")
  private Charity charity;

  @Column(name = "amount", nullable = false)
  private Double amount;


  public Donation(Double amount) {
    this.setId(null);
    this.amount = amount;
  }

  public Donation() {

  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Donation[date=" + getDateCreated());
    sb.append(", amount=" + amount);
    sb.append("]");

    return sb.toString();
  }
}
