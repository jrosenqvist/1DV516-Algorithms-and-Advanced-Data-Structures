package assignment1.binarysearchtree;

class MyIntegerBST implements A1Tree {
    private Node root;

    MyIntegerBST() {
        root = null;
    }

    @Override
    public void insert(Integer value) {
        Node node = new Node(value);
        if (root == null)
            root = node;
        else
            add(node, root);
    }

    @Override
    public Integer mostSimilarValue(Integer value) {
        if (root == null)
            return null;          
        return findMostSimilar(root, root.value, value);
    }

    @Override
    public void printByLevels() {
        if (root == null)
            System.out.println("Tree is empty");
        int height = checkTreeHeight(root);
        for (int i = 0; i <= height; i++) {
            System.out.print("Depth " + i + ": ");
            printValuesAtLevel(root, i);
            System.out.println();
        }
    }

    // Helper method for printByLevels. Prints all values at a certain level
    // and recursively continues up the tree.
    private void printValuesAtLevel(Node node, int currentLevel) {
        if (node == null)
            return;
        if (currentLevel == 0)
            System.out.print(node.value + " ");
        else {
            printValuesAtLevel(node.left, currentLevel - 1);
            printValuesAtLevel(node.right, currentLevel - 1);
        }
    }

    // Helper function for printByLevels. Traverses the tree and keeps
    // record of the depth of the lowest leaf.
    private int checkTreeHeight(Node node) {
        int leftHeight, rightHeight;
        if (node == null)
            return -1;
        leftHeight = checkTreeHeight(node.left) + 1;
        rightHeight = checkTreeHeight(node.right) + 1;

        if (leftHeight > rightHeight)
            return leftHeight;
        return rightHeight;
    }

    // Helper method to mostSimilarValue. Recursively sees what node has the closest
    // absolute value to the requested value. If the exact value is found, the method
    // immediately returns. 
    private Integer findMostSimilar(Node node, int currentBest, int value) {
        if (node.value == value)
            return value;
        if (node.left != null)
            currentBest = findMostSimilar(node.left, currentBest, value);
        if (node.right != null)
            currentBest = findMostSimilar(node.right, currentBest, value);

        if (Math.abs(node.value - value) < Math.abs(currentBest - value))
            currentBest = node.value;

        return currentBest;
    }

    // Helper method for insert. Recursively traverses the tree and
    // compares insertion value to the nodes.
    private void add(Node node, Node key) {
        if (node.value < key.value) {
            if (key.left == null)
                key.left = node;
            else
                add(node, key.left);
        }
        if (node.value > key.value) {
            if (key.right == null)
                key.right = node;
            else
                add(node, key.right);
        }
    }

    private class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }
}