package com.company;

import com.company.domain.Charity;
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
public class CharityControllerTest {

  @Inject
  @Client("/")
  HttpClient client;

  @Test
  public void testFindNonExistingCharityReturns404() {
    HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
      client.toBlocking().exchange(HttpRequest.GET("/charities/99"));
    });

    assertNotNull(thrown.getResponse());
    assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
  }

  @Test
  public void testCharityCrudOperations() {

    List<Long> charityIds = new ArrayList<>();

    HttpRequest<?> request = HttpRequest.POST
      ("/charities", Map.of("name", "RedCross", "ein", "56-4444", "description", "example"));
    HttpResponse<?> response = client.toBlocking().exchange(request);
    charityIds.add(entityId(response));

    assertEquals(HttpStatus.CREATED, response.getStatus());

    request = HttpRequest.POST("/charities", Map.of("name", "American Cancer", "ein", "56-5555", "description", "example"));
    response = client.toBlocking().exchange(request);

    assertEquals(HttpStatus.CREATED, response.getStatus());

    Long id = entityId(response);
    charityIds.add(id);
    request = HttpRequest.GET("/charities/" + id);

    Charity charity = client.toBlocking().retrieve(request, Charity.class);

    assertEquals("American Cancer", charity.getName());

    request = HttpRequest.PUT("/charities", new CharityUpdateCommand(id, "American Cancer Society"));
    response = client.toBlocking().exchange(request);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

    request = HttpRequest.GET("/charities/" + id);
    charity = client.toBlocking().retrieve(request, Charity.class);
    assertEquals("American Cancer Society", charity.getName());

    // cleanup:
    for (Long charityId : charityIds) {
      request = HttpRequest.DELETE("/charities/" + charityId);
      response = client.toBlocking().exchange(request);
      assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }
  }

  protected Long entityId(HttpResponse<?> response) {
    String path = "/charities/";
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
