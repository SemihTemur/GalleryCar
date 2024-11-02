package com.semih.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

    private String city;

    private String district;

    private String neighborhood;

    private String street;

}
