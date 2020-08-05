package com.ibatask.consumer.external;

import com.ibatask.consumer.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExternalApiService {

    public List<UserDto> getExternalUsers() {
        try {
            ResponseEntity<List<UserDto>> responseEntity = new RestTemplate()
                    .exchange("http://localhost:8100/users", HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<UserDto>>() {
                            });
            return responseEntity.getBody();
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ArrayList<>();
        }
    }
}
