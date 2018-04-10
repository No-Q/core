package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.RestaurantType;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="restaurant")
public class Restaurant extends BaseEntity{

    @Column
    private String name;
    @Column
    private Integer costPerPerson;
    @Column
    private String landmark;
    @Column(name="veg_only")
    private Boolean vegOnly;
    @Column
    private String company;
    @Column(name="restaurant_type")
    private RestaurantType type;
    @Column
    private String email;
    @Column
    private String phone;

    @OneToMany(targetEntity=Address.class, mappedBy="restaurant",
            fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Address> addresses;

    public Restaurant() {
        super();
    }

    public Restaurant(String name, int costPerPerson, String landmark,
                      Boolean vegOnly, RestaurantType type, String email,
                      Set<Address> addresses) {
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.addresses = addresses;
        this.type = type;
        this.email = email;
    }

    public Restaurant(String name, String email, String phone, Boolean vegOnly) {
        this.name = name;
        this.vegOnly = vegOnly;
        this.email = email;
        this.phone = phone;
    }

    public Restaurant(String name, String email, String phone, Boolean vegOnly, Address address) {
        super();
        this.name = name;
        this.vegOnly = vegOnly;
        this.email = email;
        this.phone = phone;
        this.addresses = new HashSet<>();
        addresses.add(address);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCostPerPerson() {
        return costPerPerson;
    }
    public void setCostPerPerson(Integer costPerPerson) {
        this.costPerPerson = costPerPerson;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Boolean getVegOnly() {
        return vegOnly;
    }

    public void setVegOnly(Boolean vegOnly) {
        this.vegOnly = vegOnly;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
