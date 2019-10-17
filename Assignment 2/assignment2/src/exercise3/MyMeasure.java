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
        }
        for (int i = 0; i < array2.length; i++) {
            if (!mht.contains(array2[i]))
                return false;
            mht.delete(array2[i]);
        }
        return true;
    }

    @Override
    public int minDifferences(int[] array1, int[] array2) {
        if (array1.length != array2.length)
            return -1;

        MergeSort ms = new MergeSort();
        ms.startSort(array1);
        ms.startSort(array2);
        
        int sum = 0;
        for (int i = 0; i < array1.length; i++) {
            sum += Math.pow(array1[i] - array2[i], 2);
        }
        
        return sum;
    }

    @Override
    public int[] getPercentileRange(int[] arr, int lower, int upper) {
        // TODO Auto-generated method stub
        return null;
    }

    private class MergeSort {
        public void startSort(int[] array) {            
            sort(array, 0, array.length - 1);            
        }

        private void sort(int[] arr, int l, int r) {
            if (l < r) {
                int m = (l + r) / 2;

                sort(arr, l, m);
                sort(arr, m + 1, r);

                merge(arr, l, m, r);
            }
        }

        private void merge(int[] arr, int l, int m, int r) {
            int left[] = new int[m - l + 1];
            int right[] = new int[r - m];
            
            for (int i = 0; i < left.length; i++)
                left[i] = arr[l + i];
            for (int i = 0; i < right.length; i++)
                right[i] = arr[m + i + 1];
            
            int li = 0, ri = 0, ai = l;

            while (li < left.length && ri < right.length) {
                if (left[li] <= right[ri]) {
                    arr[ai] = left[li];
                    li++;
                } 
                else {
                    arr[ai] = right[ri];
                    ri++;
                }
                ai++;
            }

            while (li < left.length) {
                arr[ai] = left[li];
                ai++;
                li++;
            }            

            while (ri < right.length) {
                arr[ai] = right[ri];
                ai++;
                ri++;
            }
        }
    }
}