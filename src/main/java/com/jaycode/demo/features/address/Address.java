package com.jaycode.demo.features.address;

import com.jaycode.demo.features.student.Student;
import jakarta.persistence.*;

@Entity
@Table
public class Address {
    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private  Long id;
    private  String streetName;
    private String houseNumber;

    @OneToOne
    private Student student;

    public Address(Long id, String streetName, String houseNumber) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public Address(String streetName, String houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
