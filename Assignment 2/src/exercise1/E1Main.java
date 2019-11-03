package exercise1;

import java.util.Random;

public class E1Main {
    public static void main(String[] args) {
        MyHashTable<Integer> mht = new MyHashTable<>();        

        // Randomly generate numbers that by design will collide
        int inserted[] = new int[1500];
        Random r = new Random();
        int count = 0;
        for (int i = 0; i < inserted.length; i++) {            
            int n = r.nextInt(inserted.length / 10);         
            mht.insert(n);
            inserted[i] = n;
            count++;            
        }
        
        // Check if all numbers can be found again
        count = 0;
        for (int i = 0; i < inserted.length; i++) {
             int n = inserted[i];
             if (mht.contains(n)) count++;             
        }
        System.out.println("Found " + count);
               
        // See if table works properly with strings
        MyHashTable<String> mht2 = new MyHashTable<>();               
        mht2.insert("Hello");
        mht2.insert("Hello");        
        System.out.println(mht2.contains("Hello"));  // should be true
        mht2.delete("Hello");
        System.out.println(mht2.contains("Hello")); // should be true
        mht2.delete("Hello");
        System.out.println(mht2.contains("Hello")); // should be false
    }
}