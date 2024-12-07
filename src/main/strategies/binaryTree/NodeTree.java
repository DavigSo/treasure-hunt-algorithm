package main.strategies.binaryTree;

public class NodeTree<T> {

    private T value;
    private int i;
    private int j;

    private NodeTree<T> left;
    private NodeTree<T> right;

    public NodeTree() {}
    public NodeTree(T value, int i, int j) {
        super();
        this.value = value;
        this.i = i;
        this.j = j;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }


    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


    public NodeTree<T> getLeft() {
        return left;
    }

    public void setLeft(NodeTree<T> left) {
        this.left = left;
    }


    public NodeTree<T> getRight() {
        return right;
    }

    public void setRight(NodeTree<T> right) {
        this.right = right;
    }

    public boolean isNILL(){
        return this.getValue() == null;
    }
}