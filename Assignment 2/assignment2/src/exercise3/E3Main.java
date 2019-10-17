package exercise3;

import java.util.Arrays;

class E3Main {
    public static void main(String[] args) {
        A2Measure m = new MyMeasure();
        System.out.println(m.isSameCollection(new int[]{10,1,7,10}, new int[]{1, 10, 7,10})); // should be true
        System.out.println(m.isSameCollection(new int[]{10,1,7,9}, new int[]{1, 10, 7,10})); // should be false
        System.out.println(m.isSameCollection(new int[]{1,7,10}, new int[]{1, 10, 7, 7})); // should be false

        System.out.println(m.minDifferences(new int[]{2,5,3,9}, new int[]{15,12,1,3})); // should be 86        

        int[] arr = {20, 16, 2, 4, 10, 6, 12, 8, 14, 18};
        System.out.println(Arrays.toString(m.getPercentileRange(arr, 0, 10))); // should be [2]
        System.out.println(Arrays.toString(m.getPercentileRange(arr, 10, 20))); // should be [4]
        System.out.println(Arrays.toString(m.getPercentileRange(arr, 10, 50))); // should be [4,6,8,10]
        System.out.println(Arrays.toString(m.getPercentileRange(arr, 60, 70))); // should be [14]
        System.out.println(Arrays.toString(m.getPercentileRange(arr, 0, 100))); // should be [2,4,6,8,10,12,14,16,18,20]
    }
}