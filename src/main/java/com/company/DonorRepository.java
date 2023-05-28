package com.company;

import com.company.domain.Donor;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface DonorRepository extends PageableRepository<Donor, Long> {

  Donor save(@NonNull @NotBlank String firstName, @NonNull @NotBlank String lastName, String address);

  @Transactional
  default Donor saveWithException(@NonNull @NotBlank String firstName, @NonNull @NotBlank String lastName, String address) {
    save(firstName, lastName, address);
    throw new DataAccessException("test exception");
  }

  long update(@NonNull @NotNull @Id Long id, @NonNull @NotBlank String firstName, @NotNull @NotBlank String lastName, String address);
}
