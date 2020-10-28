package a2_1801040081;
import java.lang.Comparable;
import java.util.Iterator;
import java.util.Vector;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.collections.Collection;

/**
 * @overview a collection data type that the objects
 * are sorted in ascending order
 * @attribute
 * elements SortedSet<Comparable> Vector<Comparable>
 * @object
 * A typical SortedSet is SortedSet = {elements} where
 * elements(elements)
 * @abstract_properties
 * mutable(elements) = true /\ optional(elements) = false
 */
public class SortedSet<Comparable> implements Collection{
	@DomainConstraint(type = "Vector", mutable = true ,optional = false)
    private Vector<Comparable> elements;

    /**
     * @effects initialise <tt>this</tt> to be empty
     */
    public SortedSet(){
    	elements = new Vector<>();
    }

    /**
   	 * @modifies <tt>this</tt>
   	 * @effects <pre>
     *   if x is already in this 
     *     do nothing 
     *   else 
     *     add x to this, i.e., this_post = this + {x}</pre>
     */
  	@DOpt(type=OptType.MutatorAdd)
  	public void insert(Comparable x) {
    	if (getIndex(x) < 0)
      		elements.add(x);
  	}

    /**
   	 * @modifies <tt>this</tt>
     * @effects <pre>
     *   if x is not in this 
     *     do nothing 
     *   else 
     *     remove x from this, i.e. 
     *     this_post = this - {x}</pre>
     */
  	@DOpt(type=OptType.MutatorRemove)
    public void remove(Comparable x) {
    	Comparable i = getIndex(x);
    	if (i < 0)
      		return;
    	elements.set(i, elements.lastElement());
    	elements.remove(elements.size() - 1);
  	}

    /**
   	 * @effects <pre>
     *  if x is in this 
     *    return true 
     *  else 
     *    return false</pre>
     */
  	@DOpt(type=OptType.ObserverContains)
  	public boolean isIn(int x) {
	    return (getIndex(x) >= 0);
  	}

  
  	/**
     * @effects return the cardinality of <tt>this</tt>
     */
    @DOpt(type=OptType.ObserverSize)
    public int size() {
    	return elements.size();
  	}
  
  	/**
     * @effects
     *  if this is not empty
     *    return array Integer[] of elements of this
     *  else 
     *    return null 
     */
	@DOpt(type=OptType.Observer)  
  	public Integer[] getElements() {
    	if (size() == 0)
      		return null;
    	else
      		return elements.toArray(new Integer[size()]);
  	}
  
    /**
     * @effects <pre>
   	 *  if this is empty 
     *    throw an IllegalStateException
     *  else 
     *    return an arbitrary element of this</pre>
     */
  	public int choose() throws IllegalStateException {
    	if (size() == 0)
      		throw new IllegalStateException("IntSet.choose: set is empty");
    	return (Integer)elements.lastElement();
  	}

  	/**
   	 * @effects <pre>
     *  if x is in this 
   	 *    return the index where x appears 
     *  else 
     *    return -1</pre>
     */
  	private Comparable getIndex(Comparable x) {
    	for (int i = 0; i < elements.size(); i++) {
      		if (x == elements.get(i))
        		return i;
    	}

    	return -1;
  	}
  
  	@Override
  	public String toString() {
    	if (size() == 0)
      		return "IntSet:{ }";

    	String s = "IntSet:{" + elements.elementAt(0).toString();
    	for (int i = 1; i < size(); i++) {
      		s = s + " , " + elements.elementAt(i).toString();
    	}

    	return s + "}";
  	}

  	@Override
  	public boolean equals(Object o) {
    	if (!(o instanceof IntSet))
      		return false;

    	// use Vector.equals to compare elements
    	return elements.equals(((IntSet)o).elements);
  	}
  
  	/**
   	 * @effects <pre>
     *   if this satisfies abstract properties
     *     return true 
     *   else
     *     return false</pre>
     */
  	public boolean repOK() {
    	if (elements == null)
      		return false;

    	for (int i = 0; i < elements.size(); i++) {
      		Integer x = elements.get(i); 

      	/* omitted due to the use of generic
      	if (!(x instanceof Integer))
        return false;
      	*/

	      	for (int j = i + 1; j < elements.size(); j++) {
    	    	if (elements.get(j).equals(x))
        	  		return false;
      		}
    	}
    	
    	return true;
  }
}
