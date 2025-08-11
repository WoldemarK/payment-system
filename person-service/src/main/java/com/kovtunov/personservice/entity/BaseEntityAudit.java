package com.kovtunov.personservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.io.Serializable;

@Getter
@Setter
@ToString
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class BaseEntityAudit extends BaseEntity implements Serializable {

    @JsonFormat
            (
                    shape = JsonFormat.Shape.STRING,
                    pattern = "dd-MM-yyyy HH:mm:ss"
            )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonFormat
            (
                    shape = JsonFormat.Shape.STRING,
                    pattern = "dd-MM-yyyy HH:mm:ss"
            )
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
