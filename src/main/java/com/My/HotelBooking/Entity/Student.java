package com.My.HotelBooking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mobile;

    @Transient
    private String addressId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Custom Getter and Setter for addressId
    public String getAddressId() {
        // Return addressId only if Address object and its ID are not null
        if (this.address != null && this.address.getId() != null) {
            return this.address.getId().toString();
        }
        return this.addressId;
    }

    public void setAddress(Address address) {
        this.address = address;
        // Update the transient addressId only if Address ID is non-null
        if (address != null && address.getId() != null) {
            this.addressId = address.getId().toString();
        }
    }

}
