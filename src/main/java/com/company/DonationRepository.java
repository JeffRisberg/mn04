package com.company;

import com.company.domain.Donation;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.repository.PageableRepository;
import java.util.Date;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository()
public interface DonationRepository extends PageableRepository<Donation, Long> {

  Donation save(
      @NotNull Long donorId,
      @NotNull Long charityId,
      @NotNull Double amount,
      Date dateCreated,
      Date lastUpdated);

  @Transactional
  default Donation saveWithException(
      @NotNull Long donorId,
      @NotNull Long charityId,
      @NotNull Double amount,
      @NotNull Date dateCreated,
      @NotNull Date lastUpdated) {
    save(donorId, charityId, amount, dateCreated, lastUpdated);
    throw new DataAccessException("test exception");
  }

  long update(
      @NonNull @NotNull @Id Long id,
      @NotNull Long donorId,
      @NotNull Long charityId,
      @NotNull Double amount,
      @NotNull Date dateCreated,
      @NotNull Date lastUpdated);
}
