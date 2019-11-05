package exercise1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class MyDirectedGraph implements A3Graph {
    private List<Vertex> vertices = new ArrayList<>();

    @Override
    public void addVertex(int vertex) {
        this.vertices.add(new Vertex(vertex));
    }

    @Override
    public void addEdge(int sourceVertex, int targetVertex) {
        Vertex source = vertices.get(sourceVertex);
        Vertex target = vertices.get(targetVertex);
        source.connections.add(new Edge(source, target));
        target.connections.add(new Edge(source, target));
    }

    @Override
    public boolean isConnected() {        
        return (connectedComponents().size() < 2);
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

    @Override
    public boolean isAcyclic() {
        int size = vertices.size();
        if (size < 2)
            return true;
        boolean[] visited = new boolean[size + 1];
        boolean[] origin = new boolean[size + 1];
        for (Vertex v : vertices) {
            if (acyclicHelper(v, visited, origin))
                return false;
        }
        return true;
    }

    private boolean acyclicHelper(Vertex v, boolean[] visited, boolean[] origin) {
        if (origin[v.id])
            return true;

        if (visited[v.id])
            return false;

        visited[v.id] = true;
        origin[v.id] = true;

        for (Edge e : v.connections) {
            if (acyclicHelper(e.target, visited, origin))
                return true;
        }

        origin[v.id] = false;

        return false;
    }

    @Override
    public List<List<Integer>> connectedComponents() {
        HashSet<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        for (Vertex v : vertices) {
            if (!visited.contains(v))
                connectedVisitor(v, visited, stack);
        }
        visited.clear();
        ArrayList<List<Integer>> connections = new ArrayList<>();
        while (stack.size() > 0) {
            Vertex v = stack.pop();
            if (!visited.contains(v)) {
                HashSet<Vertex> comp = new HashSet<>();
                connectedAssign(v, visited, comp);
                ArrayList<Integer> set = new ArrayList<>();
                comp.forEach(vertex -> set.add(vertex.id));
                connections.add(set);
            }
        }
        connections.forEach(list -> {
            list.forEach(n -> System.out.print(n));
            System.out.println();
        });     
        return connections;
    }

    private class Vertex {
        List<Edge> connections;
        int id;

        Vertex(int id) {
            this.id = id;
            connections = new ArrayList<>();
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
