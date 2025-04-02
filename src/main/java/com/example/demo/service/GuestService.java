package com.example.demo.service;

import com.example.demo.api.model.GuestRequest;
import com.example.demo.api.model.GuestResponse;
import com.example.demo.mapper.GuestMapper;
import com.example.demo.repository.GuestRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    public GuestService(GuestRepository guestRepository, GuestMapper guestMapper) {
        this.guestRepository = guestRepository;
        this.guestMapper = guestMapper;
    }

    public Mono<GuestResponse> registerGuest(GuestRequest guestRequest) {
        // Save Entity to DB
        return guestRepository.save(guestMapper.map(guestRequest))
                .map(guestMapper::map);
    }
}
