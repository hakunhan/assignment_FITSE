package a2_1801040081;
import java.lang.Comparable;
import java.util.Iterator;
import java.util.Vector;

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
public class SortedSet<Comparable> {
    private Vector<Comparable> elements;

    public int size() {
        return 0;
    }

    public Iterator iterator() {
        return null;
    }
}
