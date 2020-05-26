public class ArithmeticTree{
    int total;
    ArithmeticTree left;
    ArithmeticTree right;
    ArithmeticTree parent;
    String sequence;
    String opperands = "";
    int prevTimes = 1;

    public ArithmeticTree(int total, ArithmeticTree left, ArithmeticTree right, ArithmeticTree parent, String opperands){
        this.total = total;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.opperands = opperands;
    }
}