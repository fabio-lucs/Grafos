package src;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthSearch {

    private LinkedList<LinkedList<Integer>> graph;
    private Queue<LinkedList<Integer>> queue;
    private final byte white = 0, gray = 1, black = 2;
    private int number_of_vertex;
    private int[] color;

    public BreadthSearch(Graph graph) {
        this.graph = graph.getListAdj();
        this.number_of_vertex = graph.getNumber_of_vertex();
        this.queue = new LinkedList<>();

    }

    public void startBFS(int start_vertex) {
        this.color = new int[this.number_of_vertex];

        for (int i = 0; i < color.length; i++) {
            color[i] = white;
        }

        this.color[start_vertex] = gray;
        LinkedList<Integer> e = graph.get(start_vertex);
        System.out.println("Start vertex: " + start_vertex);

        this.queue.add(e);

        // passo()

        while (!this.queue.isEmpty()) {
            for (int i = 0; i < this.graph.size(); i++) {
                if (this.graph.get(i) == queue.element()) {
                    System.out.println("Start vertex: " + i);
                }
            }

            for (int i = 0; i < queue.element().size(); i++) {
                if (color[queue.element().get(i)] == white) {
                    color[queue.element().get(i)] = gray; // visited
                    System.out.println("Visiting: " + queue.element().get(i));
                    queue.add(graph.get(queue.element().get(i)));
                } else {
                    System.out.println("Jump Visit: " + queue.element().get(i));
                }
                // passo()
            }

            for (int i = 0; i < graph.size(); i++) {
                if (graph.get(i) == queue.element()) {
                    System.out.println("Black Vertice: " + i);
                    color[i] = black;
                }
            }
            // passo()
            queue.remove();
        }

    }

}
