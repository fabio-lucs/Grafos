package src;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph {

    private int number_of_vertex;
    private int number_of_edge;
    private int number_of_isolated_vertex = 0;
    private int number_of_end_vertex = 0;

    private LinkedList<LinkedList<Integer>> listAdj;

    public Graph(String txt_Path) {

        try {
            this.readerGraphByTxt(txt_Path);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            e.getMessage();
        }
    }

    public Graph(LinkedList<LinkedList<Integer>> listAdj, int number_of_vertex, int number_of_edge) {
        this.listAdj = listAdj;
        this.number_of_vertex = number_of_vertex;
        this.number_of_edge = number_of_edge;
    }

    public int getNumber_of_vertex() {
        return number_of_vertex;
    }

    public int getNumber_of_edge() {
        return number_of_edge;
    }

    public int getNumber_of_end_vertex() {
        return number_of_end_vertex;
    }

    public int getNumber_of_isolated_vertex() {
        return number_of_isolated_vertex;
    }

    LinkedList<LinkedList<Integer>> getListAdj() {
        return listAdj;
    }

    private void buildGraph(int[] vertex) {

        this.number_of_vertex = vertex[0]; // Order
        this.number_of_edge = vertex[1]; // Degree
        this.listAdj = new LinkedList<LinkedList<Integer>>(); // Instace of List

        for (int i = 0; i < number_of_vertex; i++) { // first list : vertex list
            listAdj.add(new LinkedList<Integer>());
        }

        for (int j = 2; j < vertex.length - 1; j = j + 2) { // Referenced list by first list
            listAdj.get(vertex[j]).add(vertex[j + 1]);
            listAdj.get(vertex[j + 1]).add(vertex[j]);
        }

    }

    private void getDegree_for_Each_Vertex() {
        for (int i = 0; i < this.listAdj.size(); i++) {
            int countDegree = 0;
            System.out.print("vertex degree [ " + i + " ] - ");
            for (int j = 0; j < listAdj.get(i).size(); j++) {
                countDegree++;

            }
            if (countDegree == 0) {
                this.number_of_isolated_vertex++;
            }
            if (countDegree == 1) {
                this.number_of_end_vertex++;
            }
            System.out.print(countDegree);
            System.out.println();
            System.out.println("---------------------------");

        }
        System.out.println("Número de vértices isolados " + this.getNumber_of_isolated_vertex());
        System.out.println("Número de vértices de extremidade: " + this.getNumber_of_end_vertex());
        System.out.println("---------------------------");
    }

    void printListAdj() {

        System.out.println();
        System.out.println("Grafo - Lista de Adjacência");
        System.out.println("Ordem: " + this.getNumber_of_vertex());
        System.out.println("Tamanho: " + this.getNumber_of_edge());
        System.out.println("---------------------------");
        for (int i = 0; i < this.listAdj.size(); i++) {
            System.out.print(i + "->");
            for (int v : listAdj.get(i)) {

                System.out.print(v + " -");
            }
            System.out.println("");
        }

        System.out.println("---------------------------");
        getDegree_for_Each_Vertex();
    }

    // Methods to Read and tranform str in int

    private void readerGraphByTxt(String path) throws FileNotFoundException {
        File txtFile = new File(path);
        try (Scanner sc = new Scanner(txtFile).useDelimiter("\\n")) {
            String contentTxt = "";
            String[] contentGraph;
            while (sc.hasNextLine()) {

                contentTxt += sc.nextLine() + " ";

            }

            contentGraph = contentTxt.split(" ");

            stringArrayToIntegerArray(contentGraph);

        }

    }

    private void stringArrayToIntegerArray(String[] content) {
        int[] intArray = new int[content.length];

        for (int i = 0; i < content.length; i++) {
            try {
                intArray[i] = Integer.parseInt(content[i]);
            } catch (Exception e) {
                System.out.println("Unable to parse string to int: " + e.getMessage());
            }
        }

        buildGraph(intArray);
    }

}
