package com.ibatask.usersender.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(targetEntity = UserEntity.class)
    @JsonBackReference
    private UserEntity userEntity;
}
