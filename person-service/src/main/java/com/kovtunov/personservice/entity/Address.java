package com.kovtunov.personservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Timestamp;


@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Address extends BaseEntityAudit {

    private String city;
    private String state;
    private String address;
    private String zipCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    @LastModifiedBy
    private Timestamp archived;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Countries countryId;


}
