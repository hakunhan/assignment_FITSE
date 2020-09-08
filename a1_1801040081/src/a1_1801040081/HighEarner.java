package a1_1801040081;

import utils.DomainConstraint;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;
import java.lang.Math;

/**
 * @overview provide information about flower shop's high earner customers
 * @attribute
 * id           Integer int
 * name         String
 * phoneNumber  String
 * address      String
 * income       Float
 * @object 
 * A typical HighEarner is HighEarner = {id, name, phoneNumber, address}
 * where id(id), name(name), phoneNumber(phoneNumber), address(address)
 * @abstract_properties
 * mutable(id) = false /\ optional(id) = false /\ min(id) = 10^7 /\ max(id) = 10^9 /\
 * mutable(name) = true /\ optional(name) = false /\ length(name) = 50 /\
 * mutable(phoneNumber) = true /\ optional(phoneNumber) = false /\ length(phoneNumber) = 10 /\
 * mutable(address) = true /\ optional(address) = false /\ length(address) = 100 /\
 * mutable(income) = true /\ optional(income) = false /\ min(income) = 10^7
 */
public class HighEarner extends Customer{
	@DomainConstraint(type = "String", mutable = true, optional = false, min = Math.pow(10,7))
	private float income;

	/**
     * check if income satisfied abstract properties
     *
     * @effects <pre>
     *      if id >= 1 /\ id <= 10^9
     *          return true
     *      else
     *          return false
     * </pre>
     */
	private boolean validateIncome(float income){
		return income >= Math.pow(10,7);
	}

	@Override
	protected boolean validateId(int id){
		return id >= Math.pow(10,7) && id <= Math.pow(10,9);
	}

	public HighEarner(@AttrRef("id") int id, @AttrRef("name") String name, @AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address, @AttrRef("income") float income){
		super(id,name,phoneNumber,address);
		if(!validateIncome(income)){
			System.err.println("Invalid income: " + income);
			return;
		}

		setIncome(income);
	}

	/**
	 * return high earner's income
	 *
	 * @effects <tt>return income</tt>
	 */
	@DOpt(type = OptType.Observer) @AttrRef("income")
	public float getIncome(){
		return income;
	}

	/**
	 * change high earner's income
	 *
	 * @modifies <tt>this.name</tt>
	 * @effects <pre>
	 *     if income satisfies abstract properties
	 *          return true
	 *     else
	 *          return false
	 * </pre>
	 */
	@DOpt(type = OptType.Mutator) @AttrRef("income")
	public boolean setIncome(float income) {
		if (validateIncome(income)) {
			this.income = income;
			return true;
		}
		return false;
	}
}