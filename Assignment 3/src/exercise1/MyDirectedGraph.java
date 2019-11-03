package exercise1;

import java.util.ArrayList;
import java.util.List;

public class MyDirectedGraph implements A3Graph {
    private List<Vertex> vertices;

    @Override
    public void addVertex(int vertex) {
        this.vertices.add(new Vertex(vertex));
    }

    @Override
    public void addEdge(int sourceVertex, int targetVertex) {
        Vertex source = vertices.get(sourceVertex);
        Vertex target = vertices.get(targetVertex);
        source.connections.add(new Edge(source, target));
    }

    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        return false;
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

    private class Vertex {
        int name;
        List<Edge> connections = new ArrayList<>();

        Vertex(int name) {
            this.name = name;
        }

    }

    private class Edge {
        Vertex start;
        Vertex end;

        Edge(Vertex start, Vertex end) {
            this.start = start;
            this.end = end;
        }
    }
}
