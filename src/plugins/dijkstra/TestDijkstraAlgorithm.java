/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.dijkstra;

/**
 *
 * @author guss
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



public class TestDijkstraAlgorithm {

    static List<Vertex> nodes;
    static List<Edge> edges;

    
    public static void main(String args[]) {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 1; i <= 9; i++) {
            Vertex location = new Vertex("L" + i, "L" + i);
            nodes.add(location);
        }

        addLane("L1-L2", 0, 1, 1);    addLane("L2-L1", 1, 0, 1);
        
        addLane("L2-L3", 1, 2, 1);    addLane("L3-L2", 2, 1, 1);

        addLane("L1-L5", 0, 4, 1);    addLane("L5-L1", 4, 0, 1);
        
        addLane("L3-L6", 2, 5, 1);    addLane("L6-L3", 5, 2, 1);
        
        addLane("L4-L5", 3, 4, 1);    addLane("L5-L4", 4, 3, 1);

        addLane("L5-L6", 4, 5, 11);   addLane("L6-L5", 5, 4, 1);
        
        addLane("L4-L7", 3, 6, 1);    addLane("L7-L4", 6, 3, 1);
        
        addLane("L5-L9", 4, 8, 1);    addLane("L9-L5", 8, 4, 1);
        
        addLane("L7-L8", 6, 7, 1);    addLane("L8-L7", 7, 6, 1);
        
        addLane("L8-L9", 7, 8, 1);    addLane("L9-L8", 8, 7, 1);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(1-1));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(9-1));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
        
        System.out.println(path.size());

    }

    static void addLane(String laneId, int sourceLocNo, int destLocNo,
            int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
}