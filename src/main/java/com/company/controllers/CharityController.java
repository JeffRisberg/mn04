package com.company.controllers;

import com.company.CharityRepository;
import com.company.CharityUpdateCommand;
import com.company.domain.Charity;
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
@Controller("/charities")
public class CharityController {

  protected final CharityRepository charityRepository;

  public CharityController(CharityRepository charityRepository) {
    this.charityRepository = charityRepository;
  }

  @Get("/list")
  public List<Charity> list(@Valid Pageable pageable) {
    return charityRepository.findAll(pageable).getContent();
  }

  @Get("/{id}")
  public Optional<Charity> show(Long id) {
    return charityRepository
      .findById(id);
  }

  @Post
  public HttpResponse<Charity> save(
    @Body("name") @NotBlank String name,
    @Body("ein") @NotBlank String ein,
    @Body("description") @NotBlank String description) {

    Charity charity = charityRepository.save(name, ein, description);

    return HttpResponse
      .created(charity)
      .headers(headers -> headers.location(location(charity.getId())));
  }

  @Post("/ex")
  public HttpResponse<Charity> saveExceptions(
    @Body("name") @NotBlank String name,
    @Body("ein") @NotBlank String ein,
    @Body("description") @NotBlank String description) {

    try {
      Charity charity = charityRepository.saveWithException(name, ein, description);
      return HttpResponse
        .created(charity)
        .headers(headers -> headers.location(location(charity.getId())));
    } catch (DataAccessException e) {
      return HttpResponse.noContent();
    }
  }

  @Put
  public HttpResponse update(@Body @Valid CharityUpdateCommand command) {
    charityRepository.update(command.getId(), command.getName());
    return HttpResponse
      .noContent()
      .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  public void delete(Long id) {
    charityRepository.deleteById(id);
  }

  protected URI location(Long id) {
    return URI.create("/charities/" + id);
  }

  protected URI location(Charity charity) {
    return location(charity.getId());
  }
}
