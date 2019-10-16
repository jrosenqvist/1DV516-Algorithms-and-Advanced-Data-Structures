package exercise2;

import exercise1.MyHashTable;

public class MyItinerary implements A2Itinerary<A2Direction> {    
    private A2Direction[] itinerary;
    A2Direction[] values;

    public MyItinerary(A2Direction[] array) {        
        itinerary = array;
        values = A2Direction.values();
    }

    @Override
    public A2Direction[] rotateRight() {        
        A2Direction[] rotated = new A2Direction[itinerary.length];
        for (int i = 0; i < itinerary.length; i++) {
            int d = itinerary[i].ordinal() + 1;
            if (d == values.length)
                d = 0;
            rotated[i] = values[d];            
        }
        return rotated;
    }

    @Override
    public int widthOfItinerary() {
        return calculateSize(A2Direction.LEFT, A2Direction.RIGHT);
    }

    @Override
    public int heightOfItinerary() {
        return calculateSize(A2Direction.UP, A2Direction.DOWN);
    }

    @Override
    public int[] getIntersections() {
        boolean[] intersections = new boolean[itinerary.length];
        MyHashTable<Coordinate> coordinatesTable = new MyHashTable<>();
        int x = 0, y = 0, count = 0;                
        
        for (int i = 0; i < itinerary.length; i++) {
            switch(itinerary[i]) {                
                case RIGHT: x++; break;
                case LEFT: x--; break;
                case UP: y++; break;                
                case DOWN: y--; break;                
            }            
            Coordinate c = new Coordinate(x, y);            
            if (coordinatesTable.contains(c)) {                
                intersections[i] = true;
                count++;
            }
            coordinatesTable.insert(c);            
        }
        
        int[] output = new int[count];
        count = 0;
        for (int i = 0; i < intersections.length; i++) {
            if (intersections[i]) {
                output[count++] = i;
            }
        }
        return output;
    }

    private int calculateSize(A2Direction a, A2Direction b) {
        int x = 0, y = 0, i = 0;

        while (i < itinerary.length) {
            if (itinerary[i] == a) {
                int n = sizeHelper(a, i);                                
                if (n > x)
                    x = n;
                i += n;
                continue;
            }
            if (itinerary[i] == b) {
                int n = sizeHelper(b, i);                
                if (n > y)
                    y = n;
                i += n;
                continue;
            }            
            i++;
        }
        if (x > y)
            return x;
        return y;
    }

    private int sizeHelper(A2Direction d, int index) {
        A2Direction e = values[Math.abs(d.ordinal() - 2)];         
        int n = 1;
        int i = index + 1;
        while (i < itinerary.length && itinerary[i] != e) {
            if (itinerary[i] == d)
                n++;
            i++;
        }
        System.out.println("Hoppar tillbaka från helper med riktning = " + d + " n = " + n);
        return n;
    }

    private class Coordinate {
        private int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (o == this) 
                return true;
            if (o instanceof Coordinate) {
                Coordinate c = (Coordinate) o;
                return (c.x == x) && (c.y == y);
            }
            return false;
        }

        public int hashCode() {
            return Math.abs(x) + Math.abs(y);
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
