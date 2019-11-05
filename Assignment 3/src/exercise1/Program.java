package exercise1;

class Program {
    public static void main(String[] args) {
        MyDirectedGraph dg = new MyDirectedGraph();
        dg.addVertex(0);
        dg.addVertex(1);
        dg.addVertex(2);
        dg.addVertex(3);
        dg.addVertex(4);
        dg.addEdge(0, 2);
        dg.addEdge(1, 0);
        dg.addEdge(2, 1);
        dg.addEdge(0, 3);

        System.out.println(dg.isAcyclic());
        dg.connectedComponents();
    }
}