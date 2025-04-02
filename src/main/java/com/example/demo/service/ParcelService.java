package com.example.demo.service;

import com.example.demo.api.model.ParcelRequest;
import com.example.demo.api.model.ParcelResponse;
import com.example.demo.entity.ParcelEntity;
import com.example.demo.mapper.ParcelMapper;
import com.example.demo.repository.ParcelRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final ParcelMapper parcelMapper;

    public ParcelService(ParcelRepository parcelRepository, ParcelMapper parcelMapper) {
        this.parcelRepository = parcelRepository;
        this.parcelMapper = parcelMapper;
    }

    public Mono<ParcelResponse> registerParcel(ParcelRequest parcelRequest) {
        // Convert DTO to Entity
        ParcelEntity parcelEntity = parcelMapper.map(parcelRequest);

        // Save Entity to DB
        return parcelRepository.save(parcelEntity)
                .map(parcelMapper::map);
    }

    public Flux<ParcelResponse> getParcelsForGuest(String guestId) {
        return parcelRepository.findByGuestId(guestId)
                .map(parcelMapper::map);
    }
}
