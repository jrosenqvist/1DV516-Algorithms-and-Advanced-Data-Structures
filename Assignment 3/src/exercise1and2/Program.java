package exercise1and2;

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
        System.out.println("== DIRECTED GRAPH == ");
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
        System.out.println("\n== UNDIRECTED GRAPH == ");
        System.out.println("Connected: " + ug.isConnected());
        System.out.println("Acyclic: " + ug.isAcyclic());
        ug.connectedComponents().forEach(list -> System.out.println(list));

        MyUndirectedGraph<Integer> euler = new MyUndirectedGraph<>();
        euler.addVertex(1);
        euler.addVertex(2);
        euler.addVertex(3);
        euler.addVertex(4);
        euler.addVertex(5);

        euler.addEdge(1, 5);
        euler.addEdge(5, 3);
        euler.addEdge(3, 1);
        euler.addEdge(3, 4);
        euler.addEdge(2, 4);
        euler.addEdge(2, 1);
        euler.addEdge(1, 4);
        euler.addEdge(2, 3);

        System.out.println("\n== UNDIRECTED GRAPH EULER PATH == ");
        System.out.println("Has path: " + euler.hasEulerPath());
        System.out.println(euler.eulerPath());
    }
}