package com.ibatask.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhoneDto {

    private Long id;
    private String phoneNumber;
    private UserDto userDto;
}
