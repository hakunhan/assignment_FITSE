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

	public HighEarner(@AttrRef("income") float income){
		if(!validateIncome){
			System.err.println("Invalid income: " + income);
			return;
		}

		setIncome(income);	
	}
}