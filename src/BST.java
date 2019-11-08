import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    public void put(Key key, Value val) {
        if (key == null || val == null)
            throw new IllegalArgumentException();
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if(isEmpty()) throw new NoSuchElementException();
        Node x = root;
        while(x.right != null){
            x = x.right;
        }
        return x.key;
    }

    public Key select(int k)    {
        if(k<0 || k>=root.size) return null;
        Node x = select(root,k);
        return x.key;
    }

    private Node select(Node x, int k){
        int left_size = size(x.left);
        if(k<left_size)
            return select(x.left,k);
        else if(k>left_size)
            return select(x.right,k-left_size-1);
        else // x = left_size
            return x;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else // cmp = 0
            return size(x.left);
    }

    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            throw new NoSuchElementException();
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp <  0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            throw new NoSuchElementException();
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    // void deleteMax()             // delete max key

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;

        /*




         */
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

}
