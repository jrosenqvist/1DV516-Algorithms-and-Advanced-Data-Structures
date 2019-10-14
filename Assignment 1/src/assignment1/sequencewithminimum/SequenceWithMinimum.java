package assignment1.sequencewithminimum;

import A1SequenceWithMinimum;

public class SequenceWithMinimum implements A1SequenceWithMinimum {
    SWMNode head;
    SWMNode tail;
    SWMNode minimum;

    SequenceWithMinimum() {
        head = null;
        tail = null;
        minimum = null;
    }

    @Override
    public void insertLeft(Integer value) {
        SWMNode newNode = new SWMNode(null, head, value);
        head.leftNode = newNode;
        head = newNode;
        compareToMinimum(newNode);
    }

    @Override
    public void insertRight(Integer value) {
        SWMNode newNode = new SWMNode(tail, null, value);
        tail.rightNode = newNode;
        tail = newNode;
        compareToMinimum(newNode);        
    }

    @Override
    public Integer removeLeft() {
        SWMNode removedNode = head;
        head = removedNode.rightNode;
        head.leftNode = null;
        return removedNode.value;
    }

    @Override
    public Integer removeRight() {
        SWMNode removedNode = tail;
        tail = removedNode.leftNode;
        tail.rightNode = null;
        return removedNode.value;
    }

    @Override
    public Integer findMinimum() {
        return minimum.value;
    }

    // Compare new insert with current minimum and set a new reference if necessary
    private void compareToMinimum(SWMNode newNode) {        
        if (newNode.value < minimum.value || minimum == null) {
            minimum = newNode;
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