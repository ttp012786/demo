package com.example.demo.repository;

import com.example.demo.entity.ParcelEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ParcelRepository extends ReactiveCrudRepository<ParcelEntity, String> {

    // Find parcels by guest ID
    Flux<ParcelEntity> findByGuestId(String guestId);
}