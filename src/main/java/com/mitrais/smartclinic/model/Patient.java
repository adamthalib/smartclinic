package com.mitrais.smartclinic.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    @Size(max = 60)
    private String lastName;

    private String email;

    @NotNull
    private String dob;

    @NotNull
    private String idNumber;

    //private String medicalRecordNumber;(Still find the way to generate it)

    @NotNull(message = "Please Choose 1!")
    private String gender;

    @NotNull
    private String address;

    @NotNull
    @Size(min = 5 , max = 5)
    private String postCode;

    @NotNull
    private String city;

    @NotNull
    private String province;

    private String phone1;

    private String phone2;
}
