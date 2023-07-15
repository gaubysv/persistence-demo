package org.example.persistencedemo.onetomany.embeddable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class Address {

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "ZIPCODE", nullable = false, length = 5)
    private String zipcode;

    @Column(name = "CITY", nullable = false)
    private String city;

    @OneToMany(cascade = CascadeType.PERSIST)
    // @JoinColumn(...) maps SHIPMENT table to USERS table via a direct foreign key
    // where SHIPMENT.DELIVERY_ADDRESS_USER_ID = USERS.ID
//    @JoinColumn(name = "DELIVERY_ADDRESS_USER_ID", nullable = false)
    // @JoinTable() maps SHIPMENT table to USERS table via an intermediary join table DELIVERIES
    // where SHIPMENT.ID = DELIVERIES.SHIPMENT_ID and USERS.ID = DELIVERIES.USER_ID
    @JoinTable(
            name = "DELIVERIES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SHIPMENT_ID")
    )
    private Set<Shipment> deliveries = new HashSet<>();

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Shipment> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<Shipment> deliveries) {
        this.deliveries = deliveries;
    }
}
