package mx.edu.uacm.hibernatejpamanytomanybiextras.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {
	
    @Id
    @GeneratedValue
    private Long id;

    private String street;

    private String number;

    private String postalCode;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonAddress> owners = new ArrayList<>();

    public Address() {
    }

    public Address(String street, String number, String postalCode) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public List<PersonAddress> getOwners() {
        return owners;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals( street, address.street ) &&
                Objects.equals( number, address.number ) &&
                Objects.equals( postalCode, address.postalCode );
    }

    @Override
    public int hashCode() {
        return Objects.hash( street, number, postalCode );
    }
}
