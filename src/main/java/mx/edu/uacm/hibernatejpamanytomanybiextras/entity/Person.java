package mx.edu.uacm.hibernatejpamanytomanybiextras.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

@Entity
public class Person {
	
	
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String registrationNumber;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonAddress> addresses = new ArrayList<>();

    public Person() {
    }

    public Person(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Long getId() {
        return id;
    }

    public List<PersonAddress> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address, String name) {
        PersonAddress personAddress = new PersonAddress( this, address, name );
        addresses.add( personAddress );
        address.getOwners().add(personAddress);
    }

    public void removeAddress(Address address) {
        PersonAddress personAddress = new PersonAddress( this, address);
        address.getOwners().remove( personAddress );
        addresses.remove( personAddress );
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals( registrationNumber, person.registrationNumber );
    }

    @Override
    public int hashCode() {
        return Objects.hash( registrationNumber );
    }
}
