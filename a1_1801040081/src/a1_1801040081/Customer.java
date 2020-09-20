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
public class Customer implements Comparable<Customer> {
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

    /**
     * @effects <pre>
     *       if id, name, phoneNumber, address are valid
     *           initialise this as Customer:<id,name,phoneNumber,address>
     *       else
     *           print error message
     *          </pre>
     */
    public Customer(@AttrRef("id") int id, @AttrRef("name") String name, @AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address){
        if(!validateId(id)){
            System.err.println("Invalid id: " + id);
            return;
        }
        if(!validateName(name)){
            System.err.println("Invalid name: " + name);
            return;
        }
        if(!validatePhoneNumber(phoneNumber)){
            System.err.println("Invalid phone number: " + phoneNumber);
            return;
        }
        if(!validateAddress(address)){
            System.err.println("Invalid address: " + address);
        }

        this.id = id;
        setName(name);
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }

    /**
     * return customer's id
     *
     * @effects <tt>return id</tt>
     */
    @DOpt(type = OptType.Observer) @AttrRef("id")
    public int getId() {
        return id;
    }

    /**
     * return customer's name
     *
     * @effects <tt>return name</tt>
     */
    @DOpt(type = OptType.Observer) @AttrRef("name")
    public String getName() {
        return name;
    }

    /**
     * return customer's phone number
     *
     * @effects <tt>return phone number</tt>
     */
    @DOpt(type = OptType.Observer) @AttrRef("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * return customer's address
     *
     * @effects <tt>return address</tt>
     */
    @DOpt(type = OptType.Observer) @AttrRef("address")
    public String getAddress() {
        return address;
    }

    /**
     * change customer's name
     *
     * @modifies <tt>this.name</tt>
     * @effects <pre>
     *     if name satisfies abstract properties
     *          return true
     *     else
     *          return false
     * </pre>
     */
    @DOpt(type = OptType.Mutator) @AttrRef("name")
    public boolean setName(String name) {
        if (validateName(name)) {
            this.name = name;
            return true;
        }
        return false;
    }

    /**
     * change customer's phone number
     *
     * @modifies <tt>this.phoneNumber</tt>
     * @effects <pre>
     *     if phoneNumber satisfies abstract properties
     *          return true
     *     else
     *          return false
     * </pre>
     */
    @DOpt(type = OptType.Mutator) @AttrRef("phoneNumber")
    public boolean setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    /**
     * change customer's address
     *
     * @modifies <tt>this.address</tt>
     * @effects <pre>
     *     if address satisfies abstract properties
     *          return true
     *     else
     *          return false
     * </pre>
     */
    @DOpt(type = OptType.Mutator) @AttrRef("address")
    public boolean setAddress(String address) {
        if (validateAddress(address)) {
            this.address = address;
            return true;
        }
        return false;
    }

    /**
     * check if the current object satisfies the abstract properties
     * @effects <pre>
     *     if this satisfies the abstract properties
     *          return true
     *     else
     *          return false
     * </pre>
     */
    protected boolean repOK(){
        return validateId(id) && validateName(name) && validatePhoneNumber(phoneNumber) &&validateAddress(address);
    }

    /**
     * compare a customer name with another on alphabetical order
     * @effects<pre>
     *     if this.name > customer a.name
     *          return 1
     *     else if this.name < customer a.name
     *          return -1
     *     else 
     *          return 0
     * </pre>
     */
    @Override
    public int compareTo(Customer a) {
        try{
            if(this.name.substring(0,1).toLowerCase().charAt(0) > a.name.substring(0,1).toLowerCase().charAt(0))
                return 1;
            else if(this.name.substring(0,1).toLowerCase().charAt(0) < a.name.substring(0,1).toLowerCase().charAt(0))
                return -1;
            else
                return 0;
        }catch (Exception e){
            System.err.println("Can't compare the two customers");
        }
        
        return -1;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
