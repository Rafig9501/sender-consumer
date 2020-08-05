package com.ibatask.consumer.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private Long houseNumber;

    @ManyToMany(mappedBy = "addresses")
    @JsonBackReference
    private Set<UserEntity> userEntities;
}
