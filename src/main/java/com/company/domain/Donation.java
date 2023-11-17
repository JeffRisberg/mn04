package com.company.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Serdeable
@Entity
@Table(name = "donations")
@Data
@EqualsAndHashCode(callSuper = true)
public class Donation extends AbstractItem {

  @ManyToOne(targetEntity = Donor.class)
  @JoinColumn(name = "donor_id", insertable = false, updatable = false, referencedColumnName = "id")
  @JsonIgnore
  private Donor donor;

  @Column(name = "donor_id")
  private Long donorId;

  @ManyToOne(targetEntity = Charity.class)
  @JoinColumn(
      name = "charity_id",
      insertable = false,
      updatable = false,
      referencedColumnName = "id")
  @JsonIgnore
  private Charity charity;

  @Column(name = "charity_id")
  private Long charityId;

  @Column(name = "amount", nullable = false)
  private Double amount;

  public Donation(Double amount) {
    this.setId(null);
    this.amount = amount;
  }

  public Donation(Long donorId, Long charityId, Double amount, Date dateCreated, Date lastUpdated) {
    this.setId(null);
    setDonorId(donorId);
    setCharityId(charityId);
    setAmount(amount);
    setDateCreated(dateCreated);
    setLastUpdated(lastUpdated);
  }

  public Donation() {}

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Donation[dateCreated=" + getDateCreated());
    sb.append(", lastUpdated=" + getLastUpdated());
    sb.append(", donor=" + donor);
    sb.append(", charity=" + charity);
    sb.append(", amount=" + amount);
    sb.append("]");

    return sb.toString();
  }
}
