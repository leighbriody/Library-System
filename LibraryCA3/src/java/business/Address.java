/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Leigh Briody
 */
public class Address {
    /*
    CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `address_line_1` varchar(50) DEFAULT NULL,
  `address_line_2` varchar(50) DEFAULT NULL,
  `county` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
    */
    
    //create fields 
    private int addressId;
    private String addressLine1;
    private String addressLine2;
    private String county;
    private String city;
    private String country;
    
    //Constructors
    
    public Address(){
        
    }
    
    public Address( String addressLine1 , String addressLine2 , String county , String city , String country){
        
//        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.county = county;
        this.city = city;
        this.country = country;
        
    }
    
    //Getters and setters

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    //To String

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", county=" + county + ", city=" + city + ", country=" + country + '}';
    }
    
    //Equals and hash

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.addressId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        return true;
    }
    
    //Format
    
          
}
