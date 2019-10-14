package exercise1;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        MyHashTable<Integer> mht = new MyHashTable<Integer>();
        mht.insert(3);
        mht.insert(5);
        mht.insert(7);
        mht.insert(2);
        Random r = new Random();
        for (int n = 1; n < 100; n++) {
            mht.insert(r.nextInt(1000));
        }
        mht.printTable();
        System.out.println(mht.size);
        
    }
}