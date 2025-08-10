package com.kovtunov.personservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

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
                    pattern = "dd-MM-yyyy hh:mm:ss"
            )
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonFormat
            (
                    shape = JsonFormat.Shape.STRING,
                    pattern = "dd-MM-yyyy hh:mm:ss"
            )
    @UpdateTimestamp
    private Timestamp updatedAt;
}
