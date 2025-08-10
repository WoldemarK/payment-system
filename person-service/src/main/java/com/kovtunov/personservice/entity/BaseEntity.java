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
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator
            (
                    name = "UUID",
                    strategy = "org.hibernate.id.UUIDGenerator"
            )
    @Column
            (
                    name = "id",
                    updatable = false,
                    nullable = false,
                    columnDefinition = "UUID DEFAULT uuid_generate_v4()"
            )
    private UUID id;

}
