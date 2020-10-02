package a1_1801040081;

public class Test {
    public static void main(String[] args) {
        Customer a = new Customer(123, "a", "00000", "abc");
        HighEarner b = new HighEarner(100000000, "b", "00000", "abc", 1000000000);

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.validate());

        System.out.println(a.compareTo(b));
    }
}
