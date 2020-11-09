package a2_1801040081;
import java.util.Iterator;
import java.util.Vector;

import utils.*;

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
public class SortedSet{
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
     *     add x to this, i.e., this_post = this + {x}
	 *     sort the list in ascending order</pre>
     */
  	@DOpt(type=OptType.MutatorAdd)
  	public void insert(Comparable x) {
    	if (getIndex(x) < 0)
      		elements.add(x);

		for (int i = 0; i < size(); i++){
			for (int j = i; j < size()-1; j++){
				if(elements.get(j).compareTo(elements.get(j+1)) > 0){
					Comparable temp = elements.get(j);
					elements.set(j,elements.get(j+1));
					elements.set(j+1, temp);
				}
			}
		}
  	}

    /**
   	 * @modifies <tt>this</tt>
     * @effects <pre>
     *   if x is not in this 
     *     do nothing 
     *   else 
     *     remove x from this, i.e. 
     *     this_post = this - {x}
	 *     sort the list in ascending order</pre>
     */
  	@DOpt(type=OptType.MutatorRemove)
    public void remove(Comparable x) {
    	int j = getIndex(x);
    	if (j < 0)
      		return;
    	elements.set(j, elements.lastElement());
    	elements.remove(elements.size() - 1);

		for (int i = 0; i < size(); i++){
			for (int k = i; k < size()-1; k++){
				if(elements.get(k).compareTo(elements.get(k+1)) > 0){
					Comparable temp = elements.get(k);
					elements.set(k,elements.get(k+1));
					elements.set(k+1, temp);
				}
			}
		}
  	}

    /**
   	 * @effects <pre>
     *  if x is in this 
     *    return true 
     *  else 
     *    return false</pre>
     */
  	@DOpt(type=OptType.ObserverContains)
  	public boolean isIn(Comparable x) {
	    return (getIndex(x) >= 0);
  	}

	/**
	 * @effects <pre>
	 *  if index < 0 || index >= size()
	 *    throw new NotPossibleException
	 *  else
	 *    return elements.elementsAt(index)</pre>
	 */
  	public Comparable getElement(int index){
  		if(index < 0 || index >= size()) {
			throw new NotPossibleException("Index out of range of the SortedSet!");
		}
  		return elements.elementAt(index);
	}
  
  	/**
     * @effects return the cardinality of <tt>this</tt>
     */
    @DOpt(type=OptType.ObserverSize)
    public int size() {
    	return elements.size();
  	}
  
    /**
     * @effects <pre>
   	 *  if this is empty 
     *    throw an IllegalStateException
     *  else 
     *    return an arbitrary element of this</pre>
     */
  	public Comparable choose() throws IllegalStateException {
    	if (size() == 0)
      		throw new IllegalStateException("SortedSet.choose: set is empty");
    	return (Comparable)elements.lastElement();
  	}

  	/**
   	 * @effects <pre>
     *  if x is in this 
   	 *    return the index where x appears 
     *  else 
     *    return -1</pre>
     */
  	public int getIndex(Comparable x) {
    	for (int i = 0; i < elements.size(); i++) {
      		if (x == elements.get(i))
        		return i;
    	}

    	return -1;
  	}

	/**
	 * @effects return a generator that iterate through SortedSet
	 * until there is not any element left
	 */
	public Iterator iterator(){
  		return new SortedSetGen(this);
	}

	/**
	 * @effects return a string contain elements inside SortedSet
	 */
  	@Override
  	public String toString() {
    	if (size() == 0)
      		return "SortedSet:{ }";

    	String s = "SortedSet:{" + elements.get(0).toString();
    	for (int i = 1; i < size(); i++) {
      		s = s + " , " + elements.elementAt(i).toString();
    	}

    	return s + "}";
  	}

	/**
	 * @effects
	 * 		if o is not instanceof SortedSet
	 * 			return False
	 * 		else
	 * 			return elements.equals((SortedSet)o).elements)
	 */
  	@Override
  	public boolean equals(Object o) {
    	if (!(o instanceof SortedSet))
      		return false;

    	// use Vector.equals to compare elements
    	return elements.equals(((SortedSet)o).elements);
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
			Comparable x = elements.get(i);

			for (int j = i + 1; j < elements.size(); j++) {
				if (elements.get(j).equals(x))
					return false;
			}
		}

		return true;
	}

	/**
	 * @overview An inner class that implements the Iterator interface
	 * @attribute
	 * currentElements Integer int
	 * elements SortedSet
	 * @object
	 * A typical SortedSetGen is SortedSetGen {currentElements, elements}
	 * where currentElements(currentElements), elements(elements)
	 * @abstract_properties
	 * mutable(currentElements) = false /\ optional(currentElement) = false /\
	 * min(currentElements) = 0 /\
	 * mutable(elements) = false /\ optional(elements) = false
	 */
	private static class SortedSetGen implements Iterator{
		@DomainConstraint(type = "Integer", mutable = false, optional = false, min = 0)
  		private int currentElements;
		@DomainConstraint(type = "SortedSet", mutable = false, optional = false)
		private SortedSet elements;

		/**
		 * @effects initialize this as {elements,0}
		 */
  		public SortedSetGen(@AttrRef("elements") SortedSet elements){
  			this.elements = elements;
  			this.currentElements = -1;
		}

		/**
		 * @effects if currentElements < elements.size
		 * 				return True
		 * 			else
		 * 				return False
		 */
		@Override
		public boolean hasNext() {
			return (currentElements < elements.size());
		}

		/**
		 * @effects if currentElements >= elements.size
		 * 				throw new NoMoreElementException
		 * 			else
		 * 				return invoke element.getIndex(currentElements)
		 */
		@Override
		public Object next() {
			currentElements++;
			if (currentElements >= elements.size()) {
				throw new NoMoreElementsException("There is no more Comparable class in the SortedSet!");
			}
			return elements.getElement(currentElements);
		}
	}
}