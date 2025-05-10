package com.example.demo.api;

import com.example.demo.api.model.ParcelRequest;
import com.example.demo.api.model.ParcelResponse;
import com.example.demo.service.ParcelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
public class ParcelController implements ParcelsApi {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @Override
    @GetMapping(value = "/parcels", produces = MediaType.APPLICATION_NDJSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<ParcelResponse> getParcelsForGuest(
            @NotNull @Valid @RequestParam(value = "guestId") String guestId,
            final ServerWebExchange exchange) {
        return parcelService.getParcelsForGuest(guestId);
    }

    @Override
    @PostMapping(value = "/parcels", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ParcelResponse> registerParcel(
            @Valid @RequestBody Mono<ParcelRequest> parcelRequest,
            final ServerWebExchange exchange) {
        return parcelRequest.flatMap(parcelService::registerParcel);
    }
}