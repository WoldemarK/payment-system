package com.kovtunov.personservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;
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
@Table(name = "individuals")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Individuals extends BaseEntityAudit {

    private String status;
    @Column(length = 32)

    private String phoneNumber;

    @Column(length = 32)
    private String passportNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;


}
