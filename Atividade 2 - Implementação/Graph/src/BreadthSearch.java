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
        System.out.println("Executando a busca em largura:");
        System.out.println();
        this.color = new int[this.number_of_vertex];

        for (int i = 0; i < color.length; i++) {
            color[i] = white;
        }

        this.color[start_vertex] = gray;
        LinkedList<Integer> e = graph.get(start_vertex);
        System.out.println("Vértice inicial: " + start_vertex);

        this.queue.add(e);

        // showColorsSteps();

        while (!this.queue.isEmpty()) {
            for (int i = 0; i < this.graph.size(); i++) {
                if (this.graph.get(i) == queue.element()) {
                    System.out.println("Vértice inicial: " + i);
                }
            }

            for (int i = 0; i < queue.element().size(); i++) {
                if (color[queue.element().get(i)] == white) {
                    color[queue.element().get(i)] = gray; // visited
                    System.out.println("Visitando: " + queue.element().get(i));
                    queue.add(graph.get(queue.element().get(i)));
                } else {
                    System.out.println("Pulando o visitado: " + queue.element().get(i));
                }
                // showColorsSteps();
            }

            for (int i = 0; i < graph.size(); i++) {
                if (graph.get(i) == queue.element()) {
                    System.out.println("Vértice todo visitado (preto): " + i);
                    color[i] = black;
                }
            }
            // showColorsSteps();
            queue.remove();
        }

    }

    public void find_the_shortest_way(int origin_vertex, int destiny_vertex) {
        Queue<LinkedList<Integer>> queue = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();

        path.add(origin_vertex);
        queue.add(path);

        while (!queue.isEmpty()) {
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == destiny_vertex) {
                showPath(path, origin_vertex, destiny_vertex);
                break;
            }
            LinkedList<Integer> parent_Vertex = graph.get(last);

            for (int i = 0; i < parent_Vertex.size(); i++) {
                if (notVisited(parent_Vertex.get(i), path)) {
                    LinkedList<Integer> newPath = new LinkedList<>(path);
                    newPath.add(parent_Vertex.get(i));
                    queue.add(newPath);
                }
            }
        }
    }

    private boolean notVisited(int v, LinkedList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) == v) {
                return false;
            }
        }
        return true;
    }

    private void showPath(LinkedList<Integer> path, int origin_vertex, int destiny_vertex) {
        System.out.println("-----------------");
        System.out.println("Printando o menor caminho entre " + origin_vertex + " e " + destiny_vertex + " :");
        for (Integer v : path) {
            System.out.print(v + " ");
        }
        System.out.println();
        System.out.println("-----------------");
        System.out.println();
    }

}
