package src;

public class Main {

    public static void main(String[] args) {

        // Atividade 2

        Graph g = new Graph("Graph/src/pequenoG.txt");
        g.printListAdj();

        // Atividade 3
        
        BreadthSearch bfs = new BreadthSearch(g);
        bfs.startBFS(0);



       

    }

}