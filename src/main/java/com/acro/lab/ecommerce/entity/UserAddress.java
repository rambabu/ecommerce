package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name="user_address")
public class UserAddress extends BaseEntity{
    @Column(name="user_id")
    private Long userId;
    @Column(name="line_one")
    private String lineOne;
    @Column(name="line_two")
    private String lineTwo;
    private String country;
    private String city;
    private int zipcode;
    @Column(name="contact_number")
    private int contactNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserAddress that = (UserAddress) o;
        return zipcode == that.zipcode && contactNumber == that.contactNumber && Objects.equals(userId, that.userId) && Objects.equals(lineOne, that.lineOne) && Objects.equals(lineTwo, that.lineTwo) && Objects.equals(country, that.country) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, lineOne, lineTwo, country, city, zipcode, contactNumber);
    }
}
