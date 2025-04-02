package com.example.demo.api;

import com.example.demo.config.JUnitTestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelControllerIntegrationTest extends JUnitTestcontainersConfiguration {

    @Test
    void testRegisterParcel_Success() {
        // Test successful parcel registration for a valid guest
        Map<String, String> request = Map.of("guestId", "123", "parcelDescription", "Electronics");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/parcels", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);  // Expecting status 201 Created
    }

    @Test
    void testRegisterParcel_InvalidGuest() {
        // Test trying to register a parcel for a non-existent guest
        Map<String, String> request = Map.of("guestId", "999", "parcelDescription", "Electronics");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/parcels", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);  // Expecting status 404 Not Found
    }

    @Test
    void testGetParcelsForGuest_Success() {
        // Test successfully retrieving parcels for a valid guest
        Map<String, String> request = Map.of("guestId", "123");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/parcels?guestId=123", HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);  // Expecting status 200 OK
        assertThat(response.getBody()).contains("Electronics");  // Expecting the parcel description to be in the response
    }

    @Test
    void testGetParcelsForGuest_NotFound() {
        // Test retrieving parcels for a non-existent guest
        Map<String, String> request = Map.of("guestId", "999");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/parcels?guestId=999", HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);  // Expecting status 404 Not Found
    }
}
