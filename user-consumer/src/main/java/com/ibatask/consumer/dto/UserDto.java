package com.ibatask.consumer.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<PhoneDto> phoneEntityNumbers;
    private Set<AddressDto> addresses;
}
