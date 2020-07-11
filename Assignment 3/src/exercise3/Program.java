package exercise3;

public class Program {
    public static void main(String[] args) {        
        MySocialNetwork<Integer> msn = new MySocialNetwork<>();

        for (int i = 1; i <= 15; i++)
            msn.addVertex(new Integer(i));

        msn.addEdge(1, 2);
        msn.addEdge(1, 3);
        msn.addEdge(1, new Integer(4));
        msn.addEdge(1, 5);
        msn.addEdge(2, 5);
        msn.addEdge(2, 3);
        msn.addEdge(2, 4);
        msn.addEdge(3, 4);
        msn.addEdge(3, 6);
        msn.addEdge(new Integer(3), 13);
        msn.addEdge(4, 5);
        msn.addEdge(5, 6);
        msn.addEdge(5, 7);
        msn.addEdge(5, 8);
        msn.addEdge(5, 9);
        msn.addEdge(5, 12);
        msn.addEdge(5, 15);
        msn.addEdge(6, 14);
        msn.addEdge(7, 10);
        msn.addEdge(7, 11);
        msn.addEdge(8, 10);
        msn.addEdge(9, 10);        

        System.out.println("People at distance 2: " + msn.numberOfPeopleAtFriendshipDistance(5, 2));
        System.out.println("Furthest distance: " + msn.furthestDistanceInFriendshipRelationships(5));
        System.out.println("Possible friends: " + msn.possibleFriends(5));
    }
}