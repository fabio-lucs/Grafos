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

    private int getNumber_of_vertex() {
        return number_of_vertex;
    }

    private int getNumber_of_edge() {
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
            for (int v : listAdj.get(i)) {
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
        }
    }

    void printListAdj() {
        getDegree_for_Each_Vertex();
        System.out.println();
        System.out.println("Graph - List of Adjacencies");
        System.out.println("Order: " + this.getNumber_of_vertex());
        System.out.println("Size: " + this.getNumber_of_edge());
        System.out.println("number of isolated vertices: " + this.getNumber_of_isolated_vertex());
        System.out.println("number of end vertices: " + this.getNumber_of_end_vertex());
        System.out.println("---------------------------");
        for (int i = 0; i < this.listAdj.size(); i++) {
            System.out.print(i + "->");
            for (int v : listAdj.get(i)) {

                System.out.print(v + " -");
            }
            System.out.println("");
        }

        System.out.println("---------------------------");
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
