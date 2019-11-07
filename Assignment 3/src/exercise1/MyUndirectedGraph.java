package exercise1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

public class MyUndirectedGraph<T> implements A3Graph<T> {    
    private List<Vertex> vertices = new ArrayList<>();
    
    @Override
    public void addVertex(T vertex) {
        vertices.add(new Vertex(vertex));        
    }

    @Override
    public void addEdge(T sourceVertex, T targetVertex) throws NoSuchElementException {
        Vertex source = null;
        Vertex target = null;
        for (Vertex v : vertices) {            
            if (v.id == sourceVertex) source = v;
            if (v.id == targetVertex) target = v;
            if (source != null && target != null) break;
        }
        if (source == null || target == null) {            
            throw new NoSuchElementException();
        }
        
        source.adjacent.add(target);
        target.adjacent.add(source);        
    }

    @Override
    public boolean isConnected() {
        HashSet<Vertex> connected = new HashSet<>();
        for (Vertex v : vertices) {
            for (Vertex u : v.adjacent) {
                connected.add(u);
            }
        }
        return connected.size() == vertices.size();
    }

    @Override
    public boolean isAcyclic() {
        HashSet<Vertex> visited = new HashSet<>();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex current = vertices.get(i);
            if (!visited.contains(current)) 
                if (findCycle(current, visited, null))
                    return false;
        }         
        return true;
    }

    private boolean findCycle(Vertex v, HashSet<Vertex> visited, Vertex parent) {
        visited.add(v);
        
        for (Vertex u : v.adjacent) {
            if (!visited.contains(u)) {
                if (findCycle(u, visited, v))
                    return true;
            }
            else if (u != parent)
                return true;
        }
        return false;
    }

    @Override
    public List<List<T>> connectedComponents() {
        HashSet<Vertex> connected = new HashSet<>();
        for (Vertex v : vertices) {
            for (Vertex u : v.adjacent) {
                connected.add(u);
            }
        }
        List<List<T>> listOfLists = new ArrayList<>();
        List<T> list = new ArrayList<>();
        connected.forEach(vertex -> list.add(vertex.id) );
        listOfLists.add(list);
        vertices.forEach(vertex -> {
            if (!connected.contains(vertex)) {
                ArrayList<T> l = new ArrayList<>();
                l.add(vertex.id);
                listOfLists.add(l);
            }
        });

        return listOfLists;
    }

    @Override
    public boolean hasEulerPath() {
        // TODO Auto-generated method stub
        return A3Graph.super.hasEulerPath();
    }

    @Override
    public List<T> eulerPath() {
        // TODO Auto-generated method stub
        return A3Graph.super.eulerPath();
    }

    private class Vertex {
        T id;
        List<Vertex> adjacent;

        Vertex(T id) {
            this.id = id;
            adjacent = new ArrayList<>();
        }

        public String toString() {
            return String.valueOf(id);
        }
    }
}
