package a3_1801040081.fsis;

import utils.*;

import java.lang.Math;

/**
 * @overview provide information about flower shop's high earner customers
 * @attribute
 * income       Float
 * @object 
 * A typical HighEarner is HighEarner = {id, name, phoneNumber, address}
 * where id(id), name(name), phoneNumber(phoneNumber), address(address)
 * @abstract_properties
 * P_Customer /\ min(id) = 10^7 /\ mutable(income) = true /\ optional(income) = false /\ min(income) = 10^7
 */
public class HighEarner extends Customer{
	@DomainConstraint(type = "String", mutable = true, optional = false, min = 10000000)
	private float income;

	/**
	 * check if income satisfied abstract properties
	 *
	 * @effects <pre>
	 *      if income >= 10^7
	 *          return true
	 *      else
	 *          return false
	 * </pre>
	 */
	private boolean validateIncome(float income){
		return income >= 10000000;
	}

	/**
	 * check if id satisfied abstract properties
	 * @effects <pre>
	 *      if id >= 10^7 /\ id <= 10^9
	 *          return true
	 *      else
	 *          return false
	 * </pre>
	 */
	@Override
	@DomainConstraint(type = "Integer", mutable = false, optional = false, min = 10000000, max = 1000000000)
	protected boolean validateId(int id){
		return id >= 10000000;
	}

	/**
	 * @effects <pre>
	 *       if id, name, phoneNumber, address, income are valid
	 *           initialise this as HighEarner:<id,name,phoneNumber,address,income>
	 *       else
	 *           print error message
	 *          </pre>
	 */
	public HighEarner(@AttrRef("id") int id, @AttrRef("name") String name, @AttrRef("phoneNumber") String phoneNumber,
					  @AttrRef("address") String address, @AttrRef("income") float income) throws NotPossibleException {
		super(id,name,phoneNumber,address);
                
		if(!validateIncome(income)){
			throw new NotPossibleException("HighEarner <init>: invalid argument");
		}

		this.income = income;
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
	 *          set this.income = income
	 *     else
	 *          throw NotPossibleException
	 * </pre>
	 */
	@DOpt(type = OptType.Mutator) @AttrRef("income")
	public void setIncome(float income) throws NotPossibleException{
		if (validateIncome(income)) {
			this.income = income;
		}
		else{
			throw new NotPossibleException("HighEarner.setIncome: invalid income " + income);
		}
	}

	@Override
	public String toString() {
		return "HighEarner{" +
				"id=" + super.getId() +
				", name='" + super.getName() + '\'' +
				", phoneNumber='" + super.getPhoneNumber() + '\'' +
				", address='" + super.getAddress() + '\'' +
				"income=" + income + '\'' +
				'}';
	}

	/**
	 * @effect
	 *      return a String containing the text of a simple HTML document
	 *      generated from the state of the current HighEarner
	 *      e.g. HighEarner:<10000001, "John", "12345678", "Hanoi", 10000001> invoke toHtmlDoc()
	 *      -> output:
	 *      <html>
	 *          <head><title>Customer:4-John</title></head>
	 *          <body>
	 *              4 John 12345678 Hanoi 10000001
	 *          </body>
	 *      </html>
	 */
	@Override
	@DOpt(type = OptType.Observer)
	public String toHtmlDoc(){
		String result = String.format("<html><head><title>Customer:%d-%s</title></head><body>%d %s %s %s %f</body></html>",
										super.id, super.name, super.id, super.name, super.phoneNumber, super.address, income);
		return result;
	}

	@Override
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
            return super.repOK() && validateIncome(income);
        }
}