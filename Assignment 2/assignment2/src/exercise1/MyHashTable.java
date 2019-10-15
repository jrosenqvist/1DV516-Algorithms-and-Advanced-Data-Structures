package exercise1;

public class MyHashTable<T> implements A2HashTable<T> {
    private Object[] t;
    private int size;
    private Primes primes;

    public MyHashTable() {        
        t = new Object[19];
        primes = new Primes();
        size = 0;
    }

    @Override    
    public void insert(T element) {
        Element elem = new Element(element);
        int hash = Math.abs(element.hashCode());
        int step = 1;
        int index = hash % t.length;
        while (t[index] != null) {
            index = (hash + (step * step)) % t.length;
            step++;
        }
        t[index] = elem;
        size++;
        if (size >= (t.length / 2)) {            
            try {
                rehash();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void delete(T element) {
        int hash = Math.abs(element.hashCode());
        int index = hash % t.length;
        for (int i = 0; i < t.length / 2; i++) {
            index = (hash + (i * i)) % t.length;
            if (t[index] == null) return;

            @SuppressWarnings("unchecked")
            Element elem = (Element) t[index];

            if (elem.equals(element)) {
                elem.disable();
                return;
            }
        }
    }

    @Override
    public boolean contains(T element) {
        int hash = Math.abs(element.hashCode());
        int index = hash % t.length;
        for (int i = 0; i < t.length / 2; i++) {            
            index = (hash + (i * i)) % t.length;            
            if (t[index] == null) return false;

            @SuppressWarnings("unchecked")
            Element elem = (Element) t[index];

            if (elem.isDisabled()) return false;

            if (elem.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLengthOfArray() {
        return t.length;
    }

    // Only for testing purposes, do not use on large tables
    public void printTable() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : t) {            
            sb.append(obj);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        System.out.println(sb.toString());
    }

    // Resizes table and removes elements marked as disabled
    private void rehash() throws Exception {
        int newSize = primes.getPrime(t.length * 2);
        
        Object[] old =  t;
        
        t = new Object[newSize];
        size = 0;
                
        for (int i = 0; i < old.length / 2; i++) {
            if (old[i] != null) {
                @SuppressWarnings("unchecked") 
                Element elem = (Element) old[i];
                
                if (elem.isDisabled()) continue;
                
                @SuppressWarnings("unchecked")
                T value = (T) elem.getValue();

                insert(value);                
            }            
        }        
    }

    // Container class to help optimize contains() and delete()
    private class Element {
        private boolean disabled;
        private T value;

        Element(T value) {
            disabled = false;
            this.value = value;
        }
        
        public Object getValue() { return value; }

        public boolean isDisabled() { return disabled; }

        public void disable() { disabled = true; }

        @Override
        public int hashCode() { return value.hashCode(); }

        @Override
        public boolean equals(Object o) { return value.equals(o); }
    }

    // Sieve of Eratosthenes
    private class Primes {
        private boolean[] sieve;

        Primes() {
            sieve = generateSieve(1000000);
        }

        int getPrime(int largerThan) throws Exception {
            for (int i = largerThan; i < sieve.length; i++) {
                if (sieve[i] == true) {                    
                    return i;
                }
            }            
            throw new Exception("Cannot find a new larger prime.");
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