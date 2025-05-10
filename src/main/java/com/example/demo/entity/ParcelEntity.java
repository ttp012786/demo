package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor  // Lombok to generate the no-args constructor
@ToString  // Lombok to generate the toString method
@Table("parcels")
public class ParcelEntity {

    @Id
    private String id;
    private String guestId;
    private String description;
    private boolean pickedUp;
}
