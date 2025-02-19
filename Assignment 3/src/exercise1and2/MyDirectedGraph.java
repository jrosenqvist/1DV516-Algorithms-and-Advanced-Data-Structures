package exercise1and2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MyDirectedGraph<T> implements A3Graph<T> {
    private List<Vertex> vertices = new ArrayList<>();

    @Override
    public void addVertex(T vertex) {
        this.vertices.add(new Vertex(vertex));        
    }

    @Override
    public void addEdge(T sourceVertex, T targetVertex) throws NoSuchElementException {
        Vertex source = null;
        Vertex target = null;
        for (Vertex v : vertices) {            
            if (v.id.equals(sourceVertex)) 
                source = v;
            if (v.id.equals(targetVertex))
                target = v;
            if (source != null && target != null) 
                break;
        }
        if (source == null || target == null) {            
            throw new NoSuchElementException();
        }
        Edge e = new Edge(source, target);
        target.connections.add(e);
        source.connections.add(e);
    }

    @Override
    public boolean isConnected() {
        return (connectedComponents().size() < 2);
    }
    
    @Override
    public boolean isAcyclic() {        
        HashSet<Vertex> visited = new HashSet<>(2 * vertices.size());
        HashSet<Vertex> recursion = new HashSet<>(2 * vertices.size());
        
        for (Vertex v : vertices) {
            if (findCycle(v, visited, recursion))
                return false;
        }
        return true;
    }

    private boolean findCycle(Vertex v, HashSet<Vertex> visited, HashSet<Vertex> recursion) {                
        if (!visited.contains(v)) {
            visited.add(v);
            recursion.add(v);

            for (Edge e : v.connections) {
                if (e.target == v) {
                    if (!visited.contains(e.source) && findCycle(e.source, visited, recursion))
                        return true;
                    else if (recursion.contains(e.source))
                        return true;
                }
            }            
        }        
        recursion.remove(v);
        return false;
    }

    @Override
    public List<List<T>> connectedComponents() {
        HashSet<Vertex> visited = new HashSet<>(2 * vertices.size());
        Stack<Vertex> stack = new Stack<>();
        for (Vertex v : vertices) {
            if (!visited.contains(v))
                connectedVisitor(v, visited, stack);
        }

        visited.clear();

        ArrayList<List<T>> connections = new ArrayList<>();
        while (stack.size() > 0) {
            Vertex v = stack.pop();
            if (!visited.contains(v)) {
                HashSet<Vertex> comp = new HashSet<>(2 * vertices.size());
                connectedAssign(v, visited, comp);
                ArrayList<T> set = new ArrayList<>();
                comp.forEach(vertex -> set.add(vertex.id));
                connections.add(set);
            }
        }
        
        return connections;
    }    

    private void connectedVisitor(Vertex u, HashSet<Vertex> visited, Stack<Vertex> cVerts) {
        visited.add(u);
        for (Edge e : u.connections) {
            Vertex target = e.target;
            if (target != u && !visited.contains(target))
                connectedVisitor(target, visited, cVerts);
        }
        cVerts.push(u);
    }

    private void connectedAssign(Vertex v, HashSet<Vertex> visited, HashSet<Vertex> component) {
        visited.add(v);
        component.add(v);
        for (Edge e : v.connections) {
            Vertex source = e.source;
            if (source != v && !visited.contains(source)) {
                connectedAssign(source, visited, component);
            }
        }
    }

    private class Vertex {
        List<Edge> connections;
        T id;

        Vertex(T id) {
            this.id = id;
            connections = new ArrayList<>();
        }

        public String toString() {
            return String.valueOf(id);
        }      
    }

    private class Edge {
        Vertex source;
        Vertex target;

        Edge(Vertex source, Vertex target) {
            this.source = source;
            this.target = target;
        }
    }
}
