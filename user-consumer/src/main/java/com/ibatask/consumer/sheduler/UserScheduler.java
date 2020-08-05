package com.ibatask.consumer.sheduler;

import com.ibatask.consumer.database.entity.PhoneEntity;
import com.ibatask.consumer.database.entity.UserEntity;
import com.ibatask.consumer.dto.AddressDto;
import com.ibatask.consumer.dto.PhoneDto;
import com.ibatask.consumer.dto.UserDto;
import com.ibatask.consumer.external.ExternalApiService;
import com.ibatask.consumer.service.AddressService;
import com.ibatask.consumer.service.PhoneService;
import com.ibatask.consumer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserScheduler {

    private final ExternalApiService externalApiService;
    private final UserService userService;
    private final AddressService addressService;
    private final PhoneService phoneService;

    public UserScheduler(ExternalApiService externalApiService,
                         UserService userService,
                         AddressService addressService,
                         PhoneService phoneService) {
        this.externalApiService = externalApiService;
        this.userService = userService;
        this.addressService = addressService;
        this.phoneService = phoneService;
    }

    @Scheduled(fixedDelay = 5000)
    public void updateDataInDb() {
        try {
            log.info("start to update data from external service");
            List<UserDto> externalUsers = externalApiService.getExternalUsers();

            List<AddressDto> externalUsersAddresses = externalUsers.stream()
                    .map((Function<UserDto, List<AddressDto>>) u -> new ArrayList<>(u.getAddresses()))
                    .flatMap((Function<List<AddressDto>, Stream<AddressDto>>) Collection::stream)
                    .collect(Collectors.toList());
            addressService.saveAddresses(externalUsersAddresses);

            List<UserEntity> userSaved = userService.saveUserList(externalUsers);

            List<PhoneEntity> phones = userSaved.stream().map(new Function<UserEntity, Set<PhoneEntity>>() {
                @Override
                public Set<PhoneEntity> apply(UserEntity userEntity) {
                    Set<PhoneEntity> phones = userEntity.getPhoneEntityNumbers();
                    phones.forEach(p -> p.setUserEntity(userEntity));
                    return phones;
                }
            })
                    .map((Function<Set<PhoneEntity>, List<PhoneEntity>>) ArrayList::new)
                    .flatMap((Function<List<PhoneEntity>, Stream<PhoneEntity>>) Collection::stream)
                    .collect(Collectors.toList());

            List<PhoneDto> externalUsersPhones = phones.stream()
                    .map(p -> PhoneDto.builder()
                            .id(p.getId())
                            .phoneNumber(p.getPhoneNumber())
                            .userDto(UserDto.builder()
                                    .id(p.getUserEntity().getId())
                                    .firstName(p.getUserEntity().getFirstName())
                                    .lastName(p.getUserEntity().getLastName())
                                    .build())
                            .build())
                    .collect(Collectors.toList());

            phoneService.savePhoneList(externalUsersPhones);

        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}