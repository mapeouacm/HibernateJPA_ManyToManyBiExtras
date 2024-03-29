package mx.edu.uacm.hibernatejpamanytomanybiextras.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(PersonAddressId.class)
public class PersonAddress {
	
	
	@Id
	@ManyToOne
	@JoinColumn(
			name="person_id",
			insertable = false, updatable = false
	)	
	private Person person;
	
	@Id
	@ManyToOne
	@JoinColumn(
			name="address_id",
			insertable = false, updatable = false
	)	
	private Address address;
	
	private String nameOfAddress;
	
	
	public PersonAddress() { }
	

    public PersonAddress(Person person, Address address) {
    	this.person = person;
        this.address = address;
        
    }
    
    public PersonAddress(Person person, Address address, String name) {
    	this(person, address);
    	this.nameOfAddress = name;
    }
    
    

	public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    

    public String getNameOfAddress() {
		return nameOfAddress;
	}


	public void setNameOfAddress(String nameOfAddress) {
		this.nameOfAddress = nameOfAddress;
	}


	@Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PersonAddress that = (PersonAddress) o;
        return Objects.equals( person, that.person ) &&
                Objects.equals( address, that.address );
    }

    @Override
    public int hashCode() {
        return Objects.hash( person, address );
    }

}
