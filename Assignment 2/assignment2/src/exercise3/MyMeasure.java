package exercise3;

import exercise1.MyHashTable;

public class MyMeasure implements A2Measure {

    @Override
    public boolean isSameCollection(int[] array1, int[] array2) {
        if (array1.length != array2.length) 
            return false;
        MyHashTable<Integer> mht = new MyHashTable<>();
        for (int i = 0; i < array1.length; i++) {
            mht.insert(array1[i]);
            System.out.println("Insertar " + array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            if (!mht.contains(array2[i]))
                return false;
            mht.delete(array2[i]);
            System.out.println("Tog bort " + array2[i]);
        }
        return true;
    }

    @Override
    public int minDifferences(int[] array1, int[] array2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int[] getPercentileRange(int[] arr, int lower, int upper) {
        // TODO Auto-generated method stub
        return null;
    }

}