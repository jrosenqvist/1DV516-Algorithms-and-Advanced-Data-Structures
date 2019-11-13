package exercise1and2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

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
            if (v.id == sourceVertex)
                source = v;
            if (v.id == targetVertex)
                target = v;
            if (source != null && target != null)
                break;
        }
        if (source == null || target == null) {
            throw new NoSuchElementException();
        }
        Edge e = new Edge(source, target);
        source.adjacent.add(e);
        target.adjacent.add(e);
    }

    @Override
    public boolean isConnected() {
        HashSet<Vertex> visited = new HashSet<>(2 * vertices.size());

        connectedHelper(vertices.get(0), visited);

        return visited.size() == vertices.size();
    }

    private void connectedHelper(Vertex v, HashSet<Vertex> visited) {
        visited.add(v);
        for (Edge e : v.adjacent) {
            Vertex target = e.other(v);
            if (!visited.contains(target))
                connectedHelper(target, visited);
        }
    }

    @Override
    public boolean isAcyclic() {
        HashSet<Vertex> visited = new HashSet<>(2 * vertices.size());
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

        for (Edge e : v.adjacent) {
            Vertex u = e.other(v);
            if (!visited.contains(u)) {
                if (findCycle(u, visited, v))
                    return true;
            } else if (u != parent)
                return true;
        }
        return false;
    }

    @Override
    public List<List<T>> connectedComponents() {
        HashSet<Vertex> connected = new HashSet<>(2 * vertices.size());
        for (Vertex v : vertices) {
            for (Edge e : v.adjacent) {
                Vertex u = e.other(v);
                connected.add(u);
            }
        }

        List<List<T>> listOfLists = new ArrayList<>();
        List<T> list = new ArrayList<>();
        connected.forEach(vertex -> list.add(vertex.id));
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
        // if (!isConnected())
        // return false;

        // TODO fixa så att eulerpath kan detektera lösa noder osv
        int odds = 0;

        for (Vertex v : vertices) {
            int degree = v.adjacent.size();
            if (degree % 2 != 0)
                odds++;
        }

        if (odds == 2 || odds == 0)
            return true;

        return false;
    }

    @Override
    public List<T> eulerPath() {
        Vertex start = null;
        HashSet<Edge> used = new HashSet<>(2 * vertices.size());
        Stack<Vertex> stack = new Stack<>();
        LinkedList<Vertex> path = new LinkedList<>();

        for (Vertex v : vertices) {
            if (v.adjacent.size() % 2 != 0)
                start = v;
        }
        if (start == null)
            start = vertices.get(0);

        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex v = stack.pop();
            Iterator<Edge> adjacent = v.adjacent.iterator();

            while (adjacent.hasNext()) {
                Edge edge = adjacent.next();

                if (!used.contains(edge)) {
                    used.add(edge);
                    stack.push(v);
                    v = edge.other(v);
                    adjacent = v.adjacent.iterator();
                }
            }
            path.addFirst(v);
        }
        List<T> list = new ArrayList<>();
        path.forEach(vertex -> list.add(vertex.id));
        return list;
    }

    public List<Vertex> getVertices() {
        return new ArrayList<Vertex>(vertices);
    }

    public class Vertex {
        private T id;
        private List<Edge> adjacent;

        Vertex(T id) {
            this.id = id;
            adjacent = new ArrayList<>();
        }

        public String toString() {
            return String.valueOf(id);
        }

        public T getId() {
            return id;
        }

        public List<Edge> getAdjacent() {            
            return new ArrayList<Edge>(adjacent);
        }
    }

    public class Edge {
        private Vertex source;
        private Vertex target;

        Edge(Vertex source, Vertex target) {
            this.source = source;
            this.target = target;
        }

        public Vertex getSource() {
            return source;
        }

        public Vertex getTarget() {
            return target;
        }

        public Vertex other(Vertex v) {
            return v == source ? target : source;
        }
    }
}
