package com.kovtunov.personservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
@ToString
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
            (
                    columnDefinition = "BINARY(16)",
                    updatable = false,
                    nullable = false
            )
    private UUID id;

}
