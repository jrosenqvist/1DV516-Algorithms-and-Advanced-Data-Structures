package exercise2;

import exercise1.MyHashTable;

public class MyItinerary implements A2Itinerary<A2Direction> {
    private MyHashTable<A2Direction> itinerary;    

    public MyItinerary(A2Direction[] arr) {
        itinerary = new MyHashTable<A2Direction>();        
        for (int i = 0; i < arr.length; i++)
            itinerary.insert(arr[i]);
    }

    @Override
    public A2Direction[] rotateRight() {
        while (itinerary.contains(A2Direction.DOWN)) {

        }
        return null;
    }

    @Override
    public int widthOfItinerary() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int heightOfItinerary() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int[] getIntersections() {
        // TODO Auto-generated method stub
        return null;
    }

}