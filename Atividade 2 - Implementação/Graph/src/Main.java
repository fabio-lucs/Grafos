package src;

public class Main {

    public static void main(String[] args) {

        // Atividade 2 - Lista de adjacência , ordem , grau , vértice isolado e
        // extremidade

        Graph g = new Graph("Graph/src/pequenoG.txt");
        g.printListAdj();   // Printa as informações referente ao grafo dado o txt acima

        // Atividade 3 - BFS - Busca em Largura

        BreadthSearch bfs = new BreadthSearch(g);
        bfs.startBFS(0);  // Insere o número do vértice de início


        // Atividade 3 - Imprimi o menor caminho dado 2 vértices
        int start_vertex = 0;
        int destiny_vertex = 4;

       
        bfs.find_the_shortest_way(start_vertex, destiny_vertex);

    }

}