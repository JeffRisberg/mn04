package com.company.controllers;

import com.company.CharityRepository;
import com.company.DonationRepository;
import com.company.DonationUpdateCommand;
import com.company.DonorRepository;
import com.company.domain.Donation;
import com.company.domain.Donor;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/donations")
public class DonationController {

  protected final DonationRepository donationRepository;
  protected final DonorRepository donorRepository;
  protected final CharityRepository charityRepository;

  public DonationController(
    DonationRepository donationRepository,
    DonorRepository donorRepository,
    CharityRepository charityRepository) {
    this.donationRepository = donationRepository;
    this.donorRepository = donorRepository;
    this.charityRepository = charityRepository;
  }

  @Get("/list")
  public List<Donation> list(@Valid Pageable pageable) {
    return donationRepository.findAll(pageable).getContent();
  }

  @Get("/{id}")
  public Optional<Donation> show(Long id) {
    return donationRepository.findById(id);
  }

  @Post
  public HttpResponse<Donation> save(
    @Body("donor_id") @NotNull Long donorId,
    @Body("charity_id") @NotNull Long charityId,
    @Body("amount") @NotNull Double amount) {

    LocalDateTime dateCreated = LocalDateTime.now();
    LocalDateTime lastUpdated = LocalDateTime.now();

    Donation donation =
      donationRepository.save(donorId, charityId, amount, dateCreated, lastUpdated);

    return HttpResponse.created(donation)
      .headers(headers -> headers.location(location(donation.getId())));
  }

  @Post("/ex")
  public HttpResponse<Donation> saveExceptions(
    @Body("donor_id") @NotNull Long donorId,
    @Body("charity_id") @NotNull Long charityId,
    @Body("amount") @NotNull Double amount) {

    try {
      LocalDateTime dateCreated = LocalDateTime.now();
      LocalDateTime lastUpdated = LocalDateTime.now();

      Donation donation =
        donationRepository.saveWithException(
          donorId, charityId, amount, dateCreated, lastUpdated);
      return HttpResponse.created(donation)
        .headers(headers -> headers.location(location(donation.getId())));
    } catch (DataAccessException e) {
      return HttpResponse.noContent();
    }
  }

  @Put
  public HttpResponse update(@Body @Valid DonationUpdateCommand command) {
    donationRepository.update(
      command.getId(), command.getDonor_id(), command.getCharity_id(), command.getAmount(), command.getDateCreated(), command.getLastUpdated());
    return HttpResponse.noContent()
      .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  public void delete(Long id) {
    donationRepository.deleteById(id);
  }

  protected URI location(Long id) {
    return URI.create("/donations/" + id);
  }

  protected URI location(Donation donation) {
    return location(donation.getId());
  }
}
