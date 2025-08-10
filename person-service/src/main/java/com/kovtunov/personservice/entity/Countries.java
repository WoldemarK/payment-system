package com.kovtunov.personservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Countries extends  BaseEntityAudit {

    private String name;
    private String alpha2;
    private String alpha3;
    private String status;
}
