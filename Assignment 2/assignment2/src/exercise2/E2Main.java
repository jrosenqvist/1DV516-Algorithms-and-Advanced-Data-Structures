package exercise2;

import java.util.Arrays;

public class E2Main {
    private A2Direction[] array;
    private A2Itinerary<A2Direction> itinerary;

    public static void main(String[] args) {
        E2Main p1 = new E2Main();
        p1.fillSix();
        p1.createItinerary();

        System.out.println("\n== Example 1 ==");
        System.out.println("Width: " + p1.itinerary.widthOfItinerary());
        System.out.println("Height: " + p1.itinerary.heightOfItinerary());
        System.out.println("Intersections: " + Arrays.toString(p1.itinerary.getIntersections()));

        A2Direction[] r = p1.itinerary.rotateRight();
        MyItinerary rotated = new MyItinerary(r);

        System.out.println("\n== Example 1 rotated ==");
        System.out.println("Width: " + rotated.widthOfItinerary());
        System.out.println("Height: " + rotated.heightOfItinerary());
        System.out.println("Intersections: " + Arrays.toString(rotated.getIntersections()));

        A2Direction[] example2 = { A2Direction.LEFT, A2Direction.DOWN, A2Direction.RIGHT, A2Direction.DOWN,
                A2Direction.LEFT, A2Direction.UP, A2Direction.LEFT, A2Direction.UP, A2Direction.RIGHT, A2Direction.UP };
        MyItinerary e2 = new MyItinerary(example2);

        System.out.println("\n== Example 2 ==");
        System.out.println("Width: " + e2.widthOfItinerary());
        System.out.println("Height: " + e2.heightOfItinerary());
        System.out.println("Intersections: " + Arrays.toString(e2.getIntersections()));

        A2Direction[] r2 = e2.rotateRight();
        MyItinerary e2r = new MyItinerary(r2);
        System.out.println("\n== Example 2 rotated ==");
        System.out.println("Width: " + e2r.widthOfItinerary());
        System.out.println("Height: " + e2r.heightOfItinerary());
        System.out.println("Intersections: " + Arrays.toString(e2r.getIntersections()));
    }

    private void fillSix() {
        array = new A2Direction[6];

        array[0] = A2Direction.LEFT;
        array[1] = A2Direction.DOWN;
        array[2] = A2Direction.DOWN;
        array[3] = A2Direction.RIGHT;
        array[4] = A2Direction.UP;
        array[5] = A2Direction.LEFT;
    }

    private void createItinerary() {
        itinerary = new MyItinerary(array);
    }
}