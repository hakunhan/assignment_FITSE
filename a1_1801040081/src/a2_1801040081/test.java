package a2_1801040081;
import a1_1801040081.Customer;
import java.lang.Comparable;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "a", "012", "abc");
        Comparable c1 = new Customer(1, "a", "012", "abc");
        Comparable c2 = new Customer(2, "abc", "123", "def");
        Comparable c3 = new Customer(1, "a", "012", "abc");



        SortedSet s = new SortedSet();
        s.insert(c2);
        s.insert(c1);
        s.insert(c3);
        System.out.println(s.toString());


        Iterator a = s.iter;
        System.out.println(a.hasNext());
        System.out.println(a.next().toString());
    }
}
