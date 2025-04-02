package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor  // Lombok to generate the no-args constructor
@ToString  // Lombok to generate the toString method
@Table("guests")
public class GuestEntity {

    @Id
    private String id;
    private String name;
    private String roomNumber;
    private LocalDateTime checkedOutAt;
    private boolean checkedOut;
}
