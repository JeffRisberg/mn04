package com.company.domain;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.Date;

@Serdeable
@Data
@EqualsAndHashCode(callSuper = false)
@MappedEntity(value = "donations")
public class Donation extends AbstractItem {

  @NotNull
  @MappedProperty(value = "donor_id")
  private Long donorId;

  @NotNull
  @MappedProperty(value = "charity_id")
  private Long charityId;

  @NotNull
  @MappedProperty(value = "amount")
  private Double amount;

  public Donation(Double amount) {
    this.setId(null);
    this.amount = amount;
  }

  public Donation(Long donorId, Long charityId, Double amount, Timestamp dateCreated, Timestamp lastUpdated) {
    this.setId(null);
    setDonorId(donorId);
    setCharityId(charityId);
    setAmount(amount);
    setDateCreated(dateCreated);
    setLastUpdated(lastUpdated);
  }

  public Donation() {
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Donation[dateCreated=" + getDateCreated());
    sb.append(", lastUpdated=" + getLastUpdated());
    sb.append(", donorId=" + donorId);
    sb.append(", charityId=" + charityId);
    sb.append(", amount=" + amount);
    sb.append("]");

    return sb.toString();
  }
}
