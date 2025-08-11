package com.kovtunov.personservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Address extends BaseEntityAudit {

    @Column(length = 32)
    private String city;

    @Column(length = 32)
    private String state;

    @Column(length = 128)
    private String address;

    @Column(length = 32)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Countries countryId;


}
