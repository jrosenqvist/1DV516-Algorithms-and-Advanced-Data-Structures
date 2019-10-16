package exercise3;

import java.util.Arrays;

class E3Main {
    public static void main(String[] args) {
        A2Measure m = new MyMeasure();
        System.out.println(m.isSameCollection(new int[]{10,1,7,10}, new int[]{1, 10, 7,10})); // should be true
        System.out.println(m.isSameCollection(new int[]{10,1,7,9}, new int[]{1, 10, 7,10})); // should be false
        System.out.println(m.isSameCollection(new int[]{1,7,10}, new int[]{1, 10, 7, 7})); // should be false

        System.out.println(m.minDifferences(new int[]{2,5,3,9}, new int[]{15,12,1,3})); // should be 86        
    }
}