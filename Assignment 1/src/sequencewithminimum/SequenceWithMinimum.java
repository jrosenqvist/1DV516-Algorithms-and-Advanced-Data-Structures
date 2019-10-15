package assignment1.sequencewithminimum;

public class SequenceWithMinimum implements A1SequenceWithMinimum {
    private SWMNode head;
    private SWMNode tail;
    private SWMNode minimum;
    private int size;    

    SequenceWithMinimum() {
        head = null;
        tail = null;
        minimum = null;
        size = 0;
    }

    @Override
    public void insertLeft(Integer value) {        
        SWMNode newNode = new SWMNode(null, head, value);
        if (head != null)
            head.leftNode = newNode;        
        head = newNode;
        if (tail == null)
            tail = newNode;
        compareToMinimum(newNode);
        size++;
    }

    @Override
    public void insertRight(Integer value) {
        SWMNode newNode = new SWMNode(tail, null, value);
        if (tail != null)
            tail.rightNode = newNode;
        tail = newNode;
        if (head == null)
            head = newNode;        
        compareToMinimum(newNode);        
        size++;
    }

    @Override
    public Integer removeLeft() {
        SWMNode removedNode = head;        
        head = removedNode.rightNode;
        if (removedNode == minimum) 
            minimum = null;               
       
        if (--size > 0) 
            head.leftNode = null;                                    
        else 
            head = tail = null;                    

        return removedNode.value;
    }

    @Override
    public Integer removeRight() {
        SWMNode removedNode = tail;        
        tail = removedNode.leftNode; 
        if (removedNode == minimum) 
            minimum = null;                                       
        if (--size > 0) 
            tail.rightNode = null;                            
        else 
            tail = head = null;            
        return removedNode.value;
    }

    @Override
    public Integer findMinimum() {
        if (minimum == null)
            findNewMinimum();            
            
        return minimum.value;
    }

    public Integer size() {
        return size;
    }

    public String printSequence() {
        if (head == null)
            return new String("Empty");
        StringBuilder sb = new StringBuilder();        
        SWMNode node = head;
        int count = 0;        
        while (node != null) {
            sb.append(" " + node.value);
            node = node.rightNode;
            count++;
        }
        sb.append(" [" + count + "]");
        return sb.toString();
    }

    // Compare new insert with current minimum and set a new reference if necessary
    private void compareToMinimum(SWMNode newNode) {        
        if (minimum != null)
            System.out.println("Comparing " + newNode.value + " to " + minimum.value);
        if (minimum == null || newNode.value < minimum.value) {
            minimum = newNode;
            System.out.println("New minimum set: " + minimum.value);
        }
    }

    private void findNewMinimum() {        
        SWMNode node = head;
        int min = head.value;
        while (node != null) {
            if (node.value < min) {
                minimum = node;
                min = minimum.value;
            }
            node = node.rightNode;
        }
    }

    private class SWMNode {
        SWMNode leftNode;
        SWMNode rightNode;
        Integer value;

        SWMNode(SWMNode leftNode, SWMNode rightNode, Integer value) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.value = value;
        }
    }
}