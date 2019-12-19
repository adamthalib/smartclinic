package com.mitrais.smartclinic.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class ClinicUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    @Size(max = 60)
    private String lastName;

    private String email;

    private String password;

    @NotNull
    private String dob;

    @NotNull
    private String idNumber;

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

    private Integer active;

    protected Roles role;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDob() {
        return dob;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public Integer getActive() {
        return active;
    }

    public Roles getRole() {
        return role;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getProvince() {
        return province;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
