package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.RestaurantType;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="restaurant")
@Entity
public class Restaurant extends BaseEntity{

    @Column
    private String name;
    @Column
    private Float costPerPerson;
    @Column
    private String landmark;
    @Column(name="veg_only")
    private Boolean vegOnly;
    @Column
    private String company;
    @Column(name="restaurant_type")
    private RestaurantType type;
    @Column
    private Boolean available;
    @Column
    private Date nextAvailable;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private Integer avgPreparationTime;
    @Column
    private String imageUrl;
    @Column
    private String cuisineType;

    @OneToMany(targetEntity=Address.class, mappedBy="restaurant",
            fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Address> addresses;

    @OneToMany(targetEntity=RestaurantAvailability.class, mappedBy="restaurant",
            cascade = CascadeType.ALL)
    private List<RestaurantAvailability> availability;

    @OneToMany(targetEntity=Item.class, mappedBy="restaurant",
            fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Item> items;

    public Restaurant() {
        super();
    }

    public Restaurant(String name, Float costPerPerson, String landmark,
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

    public Restaurant(String name, String email, String phone, Boolean vegOnly,Float costPerPerson,String cuisineType,
                      Integer avgPreparationTime, String imageUrl) {
        this.name = name;
        this.vegOnly = vegOnly;
        this.email = email;
        this.phone = phone;
        this.costPerPerson = costPerPerson;
        this.cuisineType = cuisineType;
        this.avgPreparationTime = avgPreparationTime;
        this.imageUrl = imageUrl;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getNextAvailable() {
        return nextAvailable;
    }

    public void setNextAvailable(Date nextAvailable) {
        this.nextAvailable = nextAvailable;
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

    public Float getCostPerPerson() {
        return costPerPerson;
    }
    public void setCostPerPerson(Float costPerPerson) {
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

    public Integer getAvgPreparationTime() {
        return avgPreparationTime;
    }

    public void setAvgPreparationTime(Integer avgPreparationTime) {
        this.avgPreparationTime = avgPreparationTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public List<RestaurantAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<RestaurantAvailability> availability) {
        this.availability = availability;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
