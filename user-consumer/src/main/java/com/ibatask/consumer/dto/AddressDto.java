package com.ibatask.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {

    private Long id;
    private String street;
    private Long houseNumber;
    private Set<UserDto> users;
}
