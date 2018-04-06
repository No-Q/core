package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.RestaurantType;

import javax.persistence.*;
import java.util.Set;

@Entity(name="restaurant")
public class Restaurant extends BaseEntity{

    @Column
    String name;

    @Column
    int costPerPerson;

    @Column
    String landmark;

    @Column(name="veg_only")
    Boolean vegOnly;

    @Column
    String company;

    @Column(name="restaurant_type")
    String type;

    @Column
    String email;

    @OneToMany(targetEntity=Address.class, mappedBy="restaurant",
            fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Address> addresses;

    public Restaurant(String name, int costPerPerson, String landmark,
                      Boolean vegOnly, RestaurantType type, String email,
                      Set<Address> addresses) {
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.addresses = addresses;
        this.type = type.name();
        this.email = email;
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

    public String getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type.name();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCostPerPerson() {
        return costPerPerson;
    }

    public void setCostPerPerson(int costPerPerson) {
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
