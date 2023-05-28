package com.company;

import com.company.domain.Charity;
import com.company.domain.Donation;
import com.company.domain.Donor;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface DonationRepository extends PageableRepository<Donation, Long> {

  Donation save(@NotNull Donor donor, @NotNull Charity charity, @NotNull double amount);

  @Transactional
  default Donation saveWithException(@NotNull Donor donor, @NotNull Charity charity, @NotNull double amount) {
    save(donor, charity, amount);
    throw new DataAccessException("test exception");
  }

  long update(@NotNull Donor donor, @NotNull Charity charity, @NotNull double amount);
}
