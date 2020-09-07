package a1_1801040081;

import utils.DomainConstraint;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;
import java.lang.Math;


/**
 * @overview provide information about flower shop's customers
 * @attribute
 * id           Integer int
 * name         String
 * phoneNumber  String
 * address      String
 * @object 
 * A typical Customer is Customer = {id, name, phoneNumber, address}
 * where id(id), name(name), phoneNumber(phoneNumber), address(address)
 * @abstract_properties
 * mutable(id) = false /\ optional(id) = false /\ min(id) = 1 /\ max(id) = 10^9 /\
 * mutable(name) = true /\ optional(name) = false /\ length(name) = 50 /\
 * mutable(phoneNumber) = true /\ optional(phoneNumber) = false /\ length(phoneNumber) = 10 /\
 * mutable(address) = true /\ optional(address) = false /\ length(address) = 100
 */
public class Customer implements Comparable {
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 1, max = 10^9)
    private int id;
    
    @DomainConstraint(type = "String", mutable = true, optional = false, length = 50)
    private String name;
    
    @DomainConstraint(type = "String", mutable = true, optional = false, length = 10)
    private String phoneNumber;
    
    @DomainConstraint(type = "String", mutable = true, optional = false, length = 100)
    private String address;
    
    /**
     * check if id satisfied abstract properties
     *
     * @effects <pre>
     *      if id >= 1 /\ id <= 10^9
     *          return true
     *      else
     *          return false
     * </pre>
     */
    protected boolean validateId(int id){
        return id >= 1 && id <= Math.pow(10,9);
    }
    
    /**
     * check if name satisfied abstract properties
     * 
     * @effects <pre>
     *      if name is not null /\ name.length() > 0 /\ name.length() <= 50
     *          return true
     *      else
     *          return false
     * </pre>
     */
    private boolean validateName(String name){
        return name != null && name.length() > 0 && name.length() <= 50;
    }
    
    /**
     * check if phoneNumber satisfied abstract properties
     * 
     * @effects <pre>
     *      if phoneNumber is not null /\ phoneNumber.length() > 0 /\ phoneNumber.length() <= 10
     *          return true
     *      else
     *          return false
     * </pre>
     */
    private boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber != null && phoneNumber.length() > 0 && phoneNumber.length() <= 10;
    }
    
    /**
     * check if address satisfied abstract properties
     * 
     * @effects <pre>
     *      if address is not null /\ address.length() > 0 /\ address.length() <= 100
     *          return true
     *      else
     *          return false
     * </pre>
     */
    private boolean validateAddress(String address){
        return address != null && address.length() > 0 && address.length() <= 100;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
