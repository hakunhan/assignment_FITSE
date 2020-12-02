package a3_1801040081.fsis;

import utils.DomainConstraint;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;
import java.lang.Math;
import java.util.Objects;

import utils.NotPossibleException;

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
 * mutable(id) = false /\ optional(id) = false /\ min(id) = 1 /\ max(id) = 1000000000 /\
 * mutable(name) = true /\ optional(name) = false /\ length(name) = 50 /\
 * mutable(phoneNumber) = true /\ optional(phoneNumber) = false /\ length(phoneNumber) = 10 /\
 * mutable(address) = true /\ optional(address) = false /\ length(address) = 100
 */
public class Customer implements Comparable<Customer>, Document{
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    protected int id;
    
    @DomainConstraint(type = "String", mutable = true, optional = false, length = LENGTH_NAME)
    protected String name;
    
    @DomainConstraint(type = "String", mutable = true, optional = false, length = LENGTH_PHONENUMBER)
    protected String phoneNumber;
    
    @DomainConstraint(type = "String", mutable = true, optional = false, length = LENGTH_ADDRESS)
    protected String address;

    private static final int MIN_ID=1;
    private static final int MAX_ID=1000000000;
    private static final int LENGTH_NAME=50;
    private static final int LENGTH_PHONENUMBER=10;
    private static final int LENGTH_ADDRESS=100;
    
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
        return id >= 1 && id <= MAX_ID;
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
        return name != null && name.length() > 0 && name.length() <= LENGTH_NAME;
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
        return phoneNumber != null && phoneNumber.length() > 0 && phoneNumber.length() <= LENGTH_PHONENUMBER;
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
        return address != null && address.length() > 0 && address.length() <= LENGTH_ADDRESS;
    }

    /**
     * @effects <pre>
     *       if id, name, phoneNumber, address are valid
     *           initialise this as Customer:<id,name,phoneNumber,address>
     *       else
     *           throw NotPossibleException
     *          </pre>
     */
    public Customer(@AttrRef("id") int id,
                    @AttrRef("name") String name,
                    @AttrRef("phoneNumber") String phoneNumber,
                    @AttrRef("address") String address)
            throws NotPossibleException{

        if (validateId(id)) {
            this.id = id;
        } else {
            throw new NotPossibleException("Customer<init>: invalid id: " + id);
        }

        if (validateName(name)) {
            this.name = name;
        } else {
            throw new NotPossibleException("Customer<init>: invalid name: " + name);
        }

        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new NotPossibleException("Customer<init>: invalid phoneNumber: " + phoneNumber);
        }

        if (validateAddress(address)) {
            this.address = address;
        } else {
            throw new NotPossibleException("Customer<init>: invalid address: " + address);
        }
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
     *          set this.name = name
     *     else
     *          throw NotPossibleException
     * </pre>
     */
    @DOpt(type = OptType.Mutator) @AttrRef("name")
    public void setName(String name) throws NotPossibleException{
        if (validateName(name)) {
            this.name = name;
        }
        else{
            throw new NotPossibleException("Customer.setName: invalid name " + name);
        }
    }

    /**
     * change customer's phone number
     *
     * @modifies <tt>this.phoneNumber</tt>
     * @effects <pre>
     *     if phoneNumber satisfies abstract properties
     *          set this.phoneNumber = phoneNumber
     *     else
     *          throw NotPossibleException
     * </pre>
     */
    @DOpt(type = OptType.Mutator) @AttrRef("phoneNumber")
    public void setPhoneNumber(String phoneNumber) throws NotPossibleException{
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new NotPossibleException("Customer.setPhoneNumber: invalid phoneNumber " + phoneNumber);
        }
    }

    /**
     * change customer's address
     *
     * @modifies <tt>this.address</tt>
     * @effects <pre>
     *     if address satisfies abstract properties
     *          set this.address = address
     *     else
     *          throw NotPossibleException
     * </pre>
     */
    @DOpt(type = OptType.Mutator) @AttrRef("address")
    public void setAddress(String address) throws NotPossibleException {
        if (validateAddress(address)) {
            this.address = address;
        } else {
            throw new NotPossibleException("Customer.setAddress: invalid address: "+ address);
        }
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
    @DOpt(type = OptType.Helper)
    protected boolean repOK(){
        return validateId(id) && validatePhoneNumber(phoneNumber) && validateName(name) && validateAddress(address);
    }

    // interface Comparable
    @Override
    public int compareTo(Customer o) {
        return compareByName(o);
    }

    /**
     * compare one customer name with another
     * @effects <pre>
     *            if o is null
     *              throw NullPointerException
     *            else if o is not a Vehicle object
     *              throw ClassCastException
     *            else
     *              return this.name.compareTo(o.name)
     *          </pre>
     */
    protected int compareByName(Customer o) throws NullPointerException, ClassCastException {
        if (o == null)
            throw new NullPointerException("Vehicle.compareByName");
        else if (!(o instanceof Customer))
            throw new ClassCastException("Vehicle.compareByName: not a Vehicle " + o);

        Customer v = (Customer) o;
        return this.name.compareTo(v.name);
    }

    /**
     * @effect
     *      return a String containing the text of a simple HTML document
     *      generated from the state of the current Customer
     *      e.g. Customer:<4, "John", "12345678", "Hanoi"> invoke toHtmlDoc()
     *      -> output:
     *      <html>
     *          <head><title>Customer:4-John</title></head>
     *          <body>
     *              4 John 12345678 Hanoi
     *          </body>
     *      </html>
     */
    @Override
    @DOpt(type = OptType.Observer)
    public String toHtmlDoc(){
        String result = String.format("<html><head><title>Customer:%d-%s</title></head><body>%d %s %s %s</body></html>", id, name, id, name, phoneNumber, address);
        return result;
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