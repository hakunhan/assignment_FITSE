package a2_1801040081;
import a1_1801040081.Customer;
import java.lang.Comparable;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        Comparable c1 = new Customer(1, "a", "012", "abc");
        Comparable c2 = new Customer(2, "abc", "123", "def");

        SortedSet s = new SortedSet();
        s.insert(c2);
        s.insert(c1);
        s.getElement(3);

        Iterator a = s.iterator();
        System.out.println(a.hasNext());
        System.out.println(a.next().toString());
    }
}
