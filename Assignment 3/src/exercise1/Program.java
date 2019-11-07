package exercise1;

class Program {
    public static void main(String[] args) {
        MyDirectedGraph<Integer> dg = new MyDirectedGraph<>();
        dg.addVertex(1);
        dg.addVertex(2);
        dg.addVertex(3);
        dg.addVertex(4);
        dg.addVertex(5);
        dg.addEdge(1, 3);
        dg.addEdge(2, 1);
        dg.addEdge(3, 2);
        dg.addEdge(1, 4);
        System.out.println("Connected: " + dg.isConnected());
        System.out.println("Acyclic: " + dg.isAcyclic());
        dg.connectedComponents().forEach(list -> System.out.println(list));

        MyUndirectedGraph<Integer> ug = new MyUndirectedGraph<>();
        ug.addVertex(1);
        ug.addVertex(2);
        ug.addVertex(3);
        ug.addVertex(4);
        ug.addVertex(5);
        ug.addEdge(3, 1);
        ug.addEdge(2, 1);
        ug.addEdge(4, 1);
        ug.addEdge(3, 2);
        System.out.println("Connected: "+ ug.isConnected());
        System.out.println("Acyclic: " + ug.isAcyclic());
        ug.connectedComponents().forEach(list -> System.out.println(list));

    }
}