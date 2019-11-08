public class BSTtest {
    public static void main(String[] args){

        BST<Integer,String> bst = new BST<Integer, String>();

        bst.put(15,"A");
        bst.put(13, "B");
        bst.put(10,"C");
        bst.put(16,"D");
        bst.put(21,"E");
        bst.put(19,"F");
        bst.put(20,"G");
        bst.put(12,"H");
        bst.put(9,"I");

        System.out.println("value for key 21 is " + bst.get(21));
        System.out.println("value for key 10 is " + bst.get(10));
        System.out.println("value for key 11 is " + bst.get(11));

        System.out.println("min key is " + bst.min());
        System.out.println("max key is " + bst.max());

        System.out.println("floor of key 5 is " + bst.floor(5));
        System.out.println("floor of key 18 is " + bst.floor(18));
        System.out.println("floor of key 12 is " + bst.floor(12));

        System.out.println("ceiling of key 5 is " + bst.ceiling(5));
        System.out.println("ceiling of key 18 is " + bst.ceiling(18));
        System.out.println("ceiling of key 12 is " + bst.ceiling(12));

        System.out.println("rank of key 16 is "  + bst.rank(16));
        System.out.println("4th key is "  + bst.select(4));

        bst.deleteMin();
        bst.delete(10);
        bst.delete(11);

        System.out.println("value for key 21 is " + bst.get(21));
        System.out.println("value for key 10 is " + bst.get(10));
        System.out.println("value for key 11 is " + bst.get(11));

    }
}
