/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.Address;

/**
 *
 * @author Brianan Johnson
 */
public interface AddressDaoInterface {
    
   
    public boolean checkIfAdddressExists(String line1,String line2, String county, String city, String country );
    
    public boolean addAddress(Address newAddress);   
    
    
    public int getLastAddressId();
    
    
    
}
