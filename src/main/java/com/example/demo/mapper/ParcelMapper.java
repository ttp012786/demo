package com.example.demo.mapper;

import com.example.demo.api.model.ParcelRequest;
import com.example.demo.api.model.ParcelResponse;
import com.example.demo.entity.ParcelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParcelMapper {

    ParcelEntity map(ParcelRequest parcelRequest);

    ParcelResponse map(ParcelEntity parcelEntity);
}
