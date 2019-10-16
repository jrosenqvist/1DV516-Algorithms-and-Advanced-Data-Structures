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
        int step = 0;
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
                System.out.println("ERROR: Maximum table size reached.");
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

            if (elem.isDisabled()) continue;

            if (elem.getValue().equals(element)) {
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
            
            if (t[index] == null) 
                return false;

            @SuppressWarnings("unchecked")
            Element elem = (Element) t[index];
            
            if (elem.isDisabled()) continue;            
            
            if (elem.getValue().equals(element)) {                            
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
                
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {
                @SuppressWarnings("unchecked") 
                Element elem = (Element) old[i];
                
                if (elem.isDisabled()) continue;
                                
                T value = elem.value;
                
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
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o instanceof MyHashTable.Element) {
                @SuppressWarnings("unchecked")
                Element e = (Element) o;
                return value.equals(e.value); 
            }
            return false;
        }
    }
    
    private class Primes {        
        public int getPrime(int largerThan) throws Exception {
            for (int i = largerThan; i < Integer.MAX_VALUE; i++) {
                if (isPrime(i)) return i;
            }
            throw new Exception("Cannot find new prime that large");
        }

        private boolean isPrime(int n) {
            for (int i = 2; i < Math.sqrt(n); i++) {
                if (n % i == 0)
                    return false;
            }
            return true;
        }
    }    
}