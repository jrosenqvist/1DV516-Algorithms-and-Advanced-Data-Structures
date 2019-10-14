package exercise1;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        MyHashTable<Integer> mht = new MyHashTable<Integer>();        
        int inserted[] = new int[15000];
        Random r = new Random();
        int count = 0;
        for (int i = 0; i < inserted.length; i++) {            
            int n = r.nextInt(inserted.length / 10);         
            mht.insert(n);
            inserted[i] = n;
            count++;            
        }               
        count = 0;
        for (int i = 0; i < inserted.length; i++) {
             int n = inserted[r.nextInt(inserted.length - 1)];
             if (mht.contains(n)) count++;             
        }
        System.out.println("Hittade " + count)        ;
        System.out.println(mht.getLengthOfArray());
        
        
        MyHashTable<String> mht2 = new MyHashTable<String>();               
        mht2.insert("Hejsan");
        mht2.insert("Hejsan");        
        System.out.println(mht2.contains("Hejsan"));
        mht2.delete("Hejsan");
        System.out.println(mht2.contains("Hejsan"));
        mht2.delete("Hejsan");
        System.out.println(mht2.contains("Hejsan"));
    }
}