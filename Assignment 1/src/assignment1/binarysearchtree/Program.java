package assignment1.binarysearchtree;

public class Program {
    public static void main(String[] args) {
        MyIntegerBST bst = new MyIntegerBST();

        bst.insert(10);
        bst.insert(7);
        bst.insert(20);
        bst.insert(25);
        bst.insert(9);
        bst.insert(4);
        bst.insert(8);

        System.out.println(bst.mostSimilarValue(18));
        System.out.println(bst.mostSimilarValue(21));
        System.out.println(bst.mostSimilarValue(24));
        System.out.println(bst.mostSimilarValue(1));
        System.out.println(bst.mostSimilarValue(9));
        System.out.println(bst.mostSimilarValue(15));

        bst.printByLevels();
    }
}