package exercise1;

import java.util.Arrays;

public class MyHashTable<T> implements A2HashTable<T> {
    private Object[] t;
    private int primes[];
    private int primesIndex;
    public int size; //TODO ändra
    private boolean primeBool[];

    MyHashTable() {
        t = new Object[9];        
        primes = generatePrimes(100000);
        primesIndex = 0;        
        size = 0;
        
    }

    @Override
    public void insert(T element) {
        int hash = element.hashCode();
        int step = 1;
        int index = hash % t.length;
        while (t[index] != null) {            
            index = (hash + (step * step)) % t.length;
            System.out.println("Kollar index " + index);
            if ((index + 1) > (t.length / 2)) {
                System.out.println("Behöver expandera");
                expandTable();
            }
            step++;
        }
        t[index] = element;        
        size++;
    }

    @Override
    public void delete(T element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean contains(T element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getLengthOfArray() {        
        return t.length;
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : t) {            
            sb.append(obj);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        System.out.println(sb.toString());
    }

    private void expandTable() {
        if (primes[primes.length - 1] < 2 * t.length) {
            generatePrimes(primeBool.length * 10);
        }        
        for (int i = primesIndex; i < primes.length; i++) {
            if (primes[i] > (2 * t.length)) {
                t = Arrays.copyOf(t, primes[i]);
                primesIndex = i;                                
                return;
            }            
        }        
    }

    // Sieve of Eratosthenes
    private int[] generatePrimes(int n) {
        int startIndex;
        if (primeBool == null) {
            primeBool = new boolean[n + 1]; 
            for(int i = 0 ; i < n; i++) 
                primeBool[i] = true; 
            startIndex = 2;
        }
        else {
            startIndex = primeBool.length - 1;
            primeBool = Arrays.copyOf(primeBool, n);
            for (int i = startIndex; i < n; i++)
                primeBool[i] = true;
        }        
          
        for(int p = startIndex; p * p <= n; p++) 
        {             
            if(primeBool[p] == true) 
            {                 
                for(int i = p * p; i <= n; i += p) 
                    primeBool[i] = false; 
            } 
        }
        int tmp[] = new int[10];
        int tmpIndex = 0;
        for (int i = 2; i <= n; i++) {
            if (primeBool[i] == true) {                
                if (tmpIndex >= tmp.length - 1) {
                    tmp = Arrays.copyOf(tmp, tmp.length * 2);
                }
                tmp[tmpIndex++] = i;                
            }        
        }
            
        return tmp;
    }
}