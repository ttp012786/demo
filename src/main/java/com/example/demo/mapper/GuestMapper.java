package com.example.demo.mapper;

import com.example.demo.api.model.GuestRequest;
import com.example.demo.api.model.GuestResponse;
import com.example.demo.entity.GuestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestEntity map(GuestRequest guestRequest);

    GuestResponse map(GuestEntity guestEntity);
}
