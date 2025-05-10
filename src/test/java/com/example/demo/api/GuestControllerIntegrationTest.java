package com.example.demo.api;

import com.example.demo.config.JUnitTestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestControllerIntegrationTest extends JUnitTestcontainersConfiguration {

    @Test
    void testRegisterGuest_Success() {
        // Test successful guest registration
        Map<String, String> request = Map.of("name", "John Doe", "roomNumber", "101");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/guests", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);  // Expecting status 201 Created
    }

    @Test
    void testRegisterGuest_InvalidData() {
        // Test invalid guest registration (missing required field)
        Map<String, String> request = Map.of("roomNumber", "101");  // Missing name
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/guests", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);  // Expecting status 400 Bad Request
    }

    @Test
    void testCheckoutGuest_Success() {
        // Test successful guest checkout
        Map<String, String> request = Map.of("guestId", "123");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/guests/123/checkout", HttpMethod.PUT, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);  // Expecting status 200 OK
    }

    @Test
    void testCheckoutGuest_NotFound() {
        // Test trying to check out a non-existent guest
        Map<String, String> request = Map.of("guestId", "999");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/guests/999/checkout", HttpMethod.PUT, entity, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);  // Expecting status 404 Not Found
    }
}
