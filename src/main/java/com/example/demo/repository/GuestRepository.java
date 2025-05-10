package com.example.demo.repository;

import com.example.demo.entity.GuestEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GuestRepository extends ReactiveCrudRepository<GuestEntity, String> {

    // Custom query to find guests who haven't checked out yet
    Flux<GuestEntity> findByCheckedOutFalse();
}