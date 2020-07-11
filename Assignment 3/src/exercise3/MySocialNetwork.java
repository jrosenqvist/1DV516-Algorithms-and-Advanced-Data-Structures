package exercise3;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import exercise1and2.*;

public class MySocialNetwork<T> extends MyUndirectedGraph<T> implements A3SocialNetwork<T> {

    @Override
    public int numberOfPeopleAtFriendshipDistance(T vertex, int distance) {
        Vertex start = findNodeFromId(vertex);
                
        return setOfNodesAtDistance(start, distance).size();
    }

    @Override
    public int furthestDistanceInFriendshipRelationships(T vertex) {
        int count = 0;
        Vertex v = findNodeFromId(vertex);
        while(setOfNodesAtDistance(v, count).size() > 0) {
            count++;
        }
        return --count;
    }

    @Override
    public List<T> possibleFriends(T vertex) {
        Vertex u = findNodeFromId(vertex);
        ArrayList<HashSet<Vertex>> nodes = listOfSetsToDistance(u, 2);
        HashSet<Vertex> distance1 = nodes.get(1);
        HashSet<Vertex> distance2 = nodes.get(2);

        List<T> possibles = new ArrayList<>();
        for (Vertex v : distance2) {
            int common = 0;
            for (Edge e: v.getAdjacent()) {
                Vertex other = e.other(v);
                if (distance1.contains(other)) 
                    common++;                
            }
            if (common >= 3)
                possibles.add(v.getId());
        }

        return possibles;
    }

    private Vertex findNodeFromId (T id) {
        Vertex node = null;
        for (Vertex v : getVertices()) {
            if (v.getId().equals(id))
                node = v;
        }
        if (node != null) {
            return node;
        }
        throw new NoSuchElementException();
    }

    private HashSet<Vertex> setOfNodesAtDistance(Vertex u, int distance) {
        return listOfSetsToDistance(u, distance).get(distance);
    }

    private ArrayList<HashSet<Vertex>> listOfSetsToDistance(Vertex u, int distance) {
        HashSet<Vertex> visited = new HashSet<>(2 * getVertices().size());
        ArrayList<HashSet<Vertex>> nodesAtDistance = new ArrayList<>();
        visited.add(u);
        nodesAtDistance.add(0, visited);
        int level = 0;
        
        while (level <= distance) {
            HashSet<Vertex> nodesAtLevel = new HashSet<>();
            
            for (Vertex v : nodesAtDistance.get(level)) {
                visited.add(v);
                
                for (Edge e : v.getAdjacent()) {
                    Vertex target = e.other(v);
                    if (!visited.contains(target)) {
                        nodesAtLevel.add(target);
                        visited.add(target);
                    }
                }
            }

            nodesAtDistance.add(++level, nodesAtLevel);
        }

        return nodesAtDistance;
    }

}
