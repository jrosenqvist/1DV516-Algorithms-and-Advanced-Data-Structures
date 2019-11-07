package exercise1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyUndirectedGraph implements A3Graph {    
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> connections = new ArrayList<>();

    @Override
    public void addVertex(int vertex) {
        vertices.add(vertex, new Vertex(vertex));        
    }

    @Override
    public void addEdge(int sourceVertex, int targetVertex) {
        Vertex source = vertices.get(sourceVertex);
        Vertex target = vertices.get(targetVertex);
        connections.add(new Edge(source, target));
    }

    @Override
    public boolean isConnected() {
        HashSet<Vertex> connected = new HashSet<>(2 * vertices.size());
        for (Edge e: connections) {
            connected.add(e.v1);
            connected.add(e.v2);
        }
        return connected.size() == vertices.size();
    }

    @Override
    public boolean isAcyclic() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<List<Integer>> connectedComponents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasEulerPath() {
        // TODO Auto-generated method stub
        return A3Graph.super.hasEulerPath();
    }

    @Override
    public List<Integer> eulerPath() {
        // TODO Auto-generated method stub
        return A3Graph.super.eulerPath();
    }

    private class Vertex {
        int id;

        Vertex(int id) {
            this.id = id;
        }
    }

    private class Edge {
        Vertex v1;
        Vertex v2;

        Edge(Vertex v1, Vertex v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
}
