package com.example.demo.api;

import com.example.demo.api.model.GuestRequest;
import com.example.demo.api.model.GuestResponse;
import com.example.demo.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@Validated
public class GuestController implements GuestsApi {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    @PostMapping(value = "/guests",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GuestResponse> registerGuest(
            @Valid @RequestBody Mono<GuestRequest> guestRequest,
            final ServerWebExchange exchange) {
        return guestRequest.flatMap(guestService::registerGuest);
    }
}
