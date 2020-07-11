package exercise1and2;

class Program {
    public static void main(String[] args) {
        MyDirectedGraph<Integer> dg = new MyDirectedGraph<>();
        dg.addVertex(new Integer(1));
        dg.addVertex(new Integer(2));
        dg.addVertex(new Integer(3));
        dg.addVertex(new Integer(4));
        dg.addVertex(new Integer(5));
        dg.addEdge(new Integer(1), new Integer(3));
        dg.addEdge(new Integer(2), new Integer(1));
        dg.addEdge(new Integer(3), new Integer(2));
        dg.addEdge(new Integer(1), new Integer(4));        
        // dg.addVertex("A");
        // dg.addVertex("B");
        // dg.addVertex("C");
        // dg.addVertex("D");
        // dg.addVertex("E");
        // dg.addEdge("A", "C");
        // dg.addEdge("B", "A");
        // dg.addEdge("C", "B");
        // dg.addEdge("A", "D");
        System.out.println("== DIRECTED GRAPH == ");
        System.out.println("Connected: " + dg.isConnected());
        System.out.println("Acyclic: " + dg.isAcyclic());
        dg.connectedComponents().forEach(list -> System.out.println(list));

        MyUndirectedGraph<Integer> ug = new MyUndirectedGraph<>();
        ug.addVertex(new Integer(1));
        ug.addVertex(new Integer(2));
        ug.addVertex(new Integer(3));
        ug.addVertex(new Integer(4));
        ug.addVertex(new Integer(5));        
        ug.addEdge(3, 1);
        ug.addEdge(2, 1);
        ug.addEdge(4, 1);
        ug.addEdge(3, 2);        
        System.out.println("\n== UNDIRECTED GRAPH == ");
        System.out.println("Connected: " + ug.isConnected());
        System.out.println("Acyclic: " + ug.isAcyclic());
        ug.connectedComponents().forEach(list -> System.out.println(list));

        MyUndirectedGraph<Integer> euler = new MyUndirectedGraph<>();
        euler.addVertex(new Integer(1));
        euler.addVertex(new Integer(2));
        euler.addVertex(new Integer(3));
        euler.addVertex(new Integer(4));
        euler.addVertex(new Integer(5));

        euler.addEdge(new Integer(1), 5);
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