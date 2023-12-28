package com.company;

import com.company.domain.Donor;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class DonorControllerTest {

  @Inject
  @Client("/")
  HttpClient client;

  @Test
  public void testFindNonExistingDonorReturns404() {
    HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
      client.toBlocking().exchange(HttpRequest.GET("/donors/99"));
    });

    assertNotNull(thrown.getResponse());
    assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
  }

  @Test
  public void testDonorCrudOperations() {

    List<Long> donorIds = new ArrayList<>();

    HttpRequest<?> request = HttpRequest.POST
      ("/donors", Map.of("firstName", "Bob", "lastName", "Jones", "address", "Main Street"));
    HttpResponse<?> response = client.toBlocking().exchange(request);
    donorIds.add(entityId(response));

    assertEquals(HttpStatus.CREATED, response.getStatus());

    request = HttpRequest.POST("/donors", Map.of("firstName", "Sally", "lastName", "Smith", "address", "University Ave"));
    response = client.toBlocking().exchange(request);

    assertEquals(HttpStatus.CREATED, response.getStatus());

    Long id = entityId(response);
    donorIds.add(id);
    request = HttpRequest.GET("/donors/" + id);

    Donor donor = client.toBlocking().retrieve(request, Donor.class);

    assertEquals("Sally", donor.getFirstName());

    request = HttpRequest.PUT("/donors", new DonorUpdateCommand(id, "Sally", "Green", "123 Tulip Lane"));
    response = client.toBlocking().exchange(request);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

    request = HttpRequest.GET("/donors/" + id);
    donor = client.toBlocking().retrieve(request, Donor.class);
    assertEquals("Sally", donor.getFirstName());
    assertEquals("Green", donor.getLastName());

    // cleanup:
    for (Long donorId : donorIds) {
      request = HttpRequest.DELETE("/donors/" + donorId);
      response = client.toBlocking().exchange(request);
      assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }
  }

  protected Long entityId(HttpResponse<?> response) {
    String path = "/donors/";
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
