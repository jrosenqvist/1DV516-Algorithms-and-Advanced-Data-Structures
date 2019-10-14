package exercise1;

import javax.naming.SizeLimitExceededException;

public class MyHashTable<T> implements A2HashTable<T> {
    private Object[] t;
    private int size;
    private Primes primes;

    MyHashTable() {
        t = new Object[19];
        primes = new Primes();
        size = 0;
    }

    @Override    
    public void insert(T element) {
        int hash = Math.abs(element.hashCode());
        int step = 1;
        int index = hash % t.length;
        while (t[index] != null) {
            index = (hash + (step * step)) % t.length;
            step++;
        }
        t[index] = element;
        size++;
        if (size >= (t.length / 2)) {
            System.out.println("Behöver expandera, antal element " + size);
            try {
                rehash();
            } catch (SizeLimitExceededException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void delete(T element) {
        int hash = Math.abs(element.hashCode());
        int index = hash % t.length;
        for (int i = 0; i < t.length; i++) {
            index = (hash + (i * i)) % t.length;
            if (t[index] == null) continue;

            if (t[index].equals(element)) {
                t[index] = null;
                return;
            }
        }
    }

    @Override
    public boolean contains(T element) {
        int hash = Math.abs(element.hashCode());
        int index = hash % t.length;
        for (int i = 0; i < t.length; i++) {            
            index = (hash + (i * i)) % t.length;            
            if (t[index] == null) continue;

            if (t[index].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLengthOfArray() {
        return t.length;
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : t) {
            // if (obj == null)
            // continue;
            sb.append(obj);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        System.out.println(sb.toString());
    }

    private void rehash() throws SizeLimitExceededException {
        int newSize = primes.getPrime(t.length * 2);

        @SuppressWarnings("unchecked")
        T[] old = (T[]) t;
        
        t = new Object[newSize];
        size = 0;
                
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {                  
                insert(old[i]);                
            }            
        }        
    }

    // Sieve of Eratosthenes
    private class Primes {
        private boolean[] sieve;

        Primes() {
            sieve = generateSieve(1000000);
        }

        int getPrime(int largerThan) throws SizeLimitExceededException {
            for (int i = largerThan; i < sieve.length; i++) {
                if (sieve[i] == true) {
                    System.out.println("Ska vara större än " + largerThan + ", hittade " + i);
                    return i;
                }
            }
            throw new SizeLimitExceededException("Cannot find a new larger prime.");
        }

        private boolean[] generateSieve(int size) {
            boolean[] prime = new boolean[size + 1];
            for (int i = 0; i < size; i++) {
                prime[i] = true;
            }
            for (int p = 2; p * p <= size; p++) {
                if (prime[p] == true) {
                    for (int i = p * p; i <= size; i += p) {
                        prime[i] = false;
                    }
                }
            }
            return prime;
        }
    }
}