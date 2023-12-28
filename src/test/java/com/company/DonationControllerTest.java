package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.company.domain.Donation;
import io.micronaut.http.*;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.Map;
import org.junit.jupiter.api.Test;

@MicronautTest
public class DonationControllerTest {

  @Inject
  @Client("/")
  HttpClient client;

  @Test
  public void testDonationCrudOperations() {
    final double donationAmount = 1234.56;
    Long charityId = 0L;
    Long donorId = 0L;
    Long donationId = 0L;

    // create charity
    HttpRequest<?> request = HttpRequest.POST
      ("/charities", Map.of("name", "Red Cross", "ein", "56-4444", "description", "example"));
    HttpResponse<?> response = client.toBlocking().exchange(request);
    charityId = entityId(response, "/charities/");

    assertEquals(HttpStatus.CREATED, response.getStatus());

    // create donor
    request = HttpRequest.POST("/donors",
      Map.of("firstName", "Sally", "lastName", "Smith", "address", "University Ave"));
    response = client.toBlocking().exchange(request);
    donorId = entityId(response, "/donors/");

    assertEquals(HttpStatus.CREATED, response.getStatus());

    // create donation
    request = HttpRequest.POST("/donations",
      Map.of("donor_id", donorId, "charity_id", charityId, "amount", donationAmount));
    response = client.toBlocking().exchange(request);
    donationId = entityId(response, "/donations/");

    // check donation
    request = HttpRequest.GET("/donations/" + donationId);
    Donation donation = client.toBlocking().retrieve(request, Donation.class);
    assertEquals(donationAmount, donation.getAmount());

    // delete donation
    request = HttpRequest.DELETE("/donations/" + donationId);
    response = client.toBlocking().exchange(request);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

    // delete donor
    request = HttpRequest.DELETE("/donors/" + donorId);
    response = client.toBlocking().exchange(request);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

    // delete charity
    request = HttpRequest.DELETE("/charities/" + charityId);
    response = client.toBlocking().exchange(request);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
  }

  protected Long entityId(HttpResponse<?> response, String path) {
    String value = response.header(HttpHeaders.LOCATION);
    if (value == null) {
      return null;
    }
    int index = value.indexOf(path);
    if (index != -1) {
      return Long.valueOf(value.substring(index + path.length()));
    }
    return null;
  }
}
