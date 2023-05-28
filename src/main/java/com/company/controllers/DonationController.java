package com.company.controllers;

import com.company.DonationRepository;
import com.company.DonationUpdateCommand;
import com.company.DonorRepository;
import com.company.DonorUpdateCommand;
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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/donations")
public class DonationController {

  protected final DonationRepository donationRepository;

  public DonationController(DonationRepository donationRepository) {
    this.donationRepository = donationRepository;
  }

  @Get("/list")
  public List<Donation> list(@Valid Pageable pageable) {
    return donationRepository.findAll(pageable).getContent();
  }


  @Get("/{id}")
  public Optional<Donation> show(Long id) {
    return donationRepository
      .findById(id);
  }
/*

  @Post
  public HttpResponse<Donor> save(
    @Body("firstName") @NotBlank String firstName,
    @Body("lastName") @NotBlank String lastName,
    @Body("address") @NotBlank String address) {
    Donation donation = donationRepository.save(1L, 1L, 100.0);

    return HttpResponse
      .created(donation)
      .headers(headers -> headers.location(location(donation.getId())));
  }

  @Post("/ex")
  public HttpResponse<Donor> saveExceptions(
    @Body("firstName") @NotBlank String firstName,
    @Body("lastName") @NotBlank String lastName,
    @Body("address") @NotBlank String address) {

    try {
      Donation doantion = donationRepository.saveWithException(firstName, lastName, address);
      return HttpResponse
        .created(doantion)
        .headers(headers -> headers.location(location(doantion.getId())));
    } catch (DataAccessException e) {
      return HttpResponse.noContent();
    }
  }

  @Put
  public HttpResponse update(@Body @Valid DonationUpdateCommand command) {
    donationRepository.update(command.getId(), command.getDonor_id(), command.getDonor_id(), command.getAmount());
    return HttpResponse
      .noContent()
      .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
  }
*/

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  public void delete(Long id) {
    donationRepository.deleteById(id);
  }

  protected URI location(Long id) {
    return URI.create("/donors/" + id);
  }

  protected URI location(Donor donor) {
    return location(donor.getId());
  }
}
