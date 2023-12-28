package com.company;

import com.company.domain.Donation;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface DonationRepository extends PageableRepository<Donation, Long> {

  Donation save(
    @NotNull Long donorId,
    @NotNull Long charityId,
    @NotNull Double amount,
    LocalDateTime dateCreated,
    LocalDateTime lastUpdated);

  @Transactional
  default Donation saveWithException(
    @NotNull Long donorId,
    @NotNull Long charityId,
    @NotNull Double amount,
    @NotNull LocalDateTime dateCreated,
    @NotNull LocalDateTime lastUpdated) {
    save(donorId, charityId, amount, dateCreated, lastUpdated);
    throw new DataAccessException("test exception");
  }

  long update(
    @NonNull @NotNull @Id Long id,
    @NotNull Long donorId,
    @NotNull Long charityId,
    @NotNull Double amount,
    @NotNull LocalDateTime dateCreated,
    @NotNull LocalDateTime lastUpdated);
}
