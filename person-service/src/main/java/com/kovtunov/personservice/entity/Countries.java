package com.kovtunov.personservice.entity;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Countries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(length = 2)
    private String alpha2;

    @Column(length = 3)
    private String alpha3;

    @Column(length = 32)
    private String status;
}
