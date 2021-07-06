
package graph_theory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;
import javafx.util.Pair;

public class Dijkstra_treeset {

    
    static int vertices;
    static LinkedList<Edge>[] adj;
   private static void printDijkstra(int[] dist, int s) {
        System.out.println("Dijkstra Algorithm: (Adjacency List + TreeSet)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: " + s + " to vertex " +   + i +
                        " distance: " + dist[i]);
            }
    }
        Dijkstra_treeset(int V){
            this.vertices=V;
            adj=new LinkedList[vertices];
            for(int i=0;i<vertices;i++)
            {
                adj[i]=new LinkedList<>();
            }
            
        }
    
      public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adj[source].addFirst(edge);

            edge = new Edge(destination, source, weight);
            adj[destination].addFirst(edge); //for undirected graph
        }
     static int[] shortestReach(int n, int[][] edges, int s) {
         Dijkstra_treeset dj=new Dijkstra_treeset(n+1);
            for(int i=0;i<edges.length;i++)
            {
                dj.addEdge(edges[i][0], edges[i][1], edges[i][2]);
            }
            for(int i=0;i<adj.length;i++)
            {
                for(int j=0;j<adj[i].size();j++)
                {
                    System.out.print(" "+adj[i].get(j).weight);
                }
                System.out.println("");
            }
         boolean[] inSPT = new boolean[6];
            //distance used to store the distance of vertex from a source
            int [] distance = new int[6];

            //Initialize all the distances to infinity
            for (int i = 0; i <vertices ; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            //Initialize priority queue
            //override the comparator to do the sorting based keys
            TreeSet treeSet = new TreeSet<>(new PairComparator());
            //create the pair for for the first index, 0 distance 0 index
            distance[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[0],0);
            //add it to tree set
            treeSet.add(p0);

            //while priority queue is not empty
            while(!treeSet.isEmpty()){
                //extract the min
                Pair<Integer, Integer> extractedPair = (Pair<Integer, Integer>) treeSet.pollFirst();

                //extracted vertex
                int extractedVertex = extractedPair.getValue();
                if(inSPT[extractedVertex]==false) {
                    inSPT[extractedVertex] = true;

                    //iterate through all the adjacent vertices and update the keys
                    LinkedList<Edge> list = adj[extractedVertex];
                    for (int i = 0; i < list.size(); i++) {
                        Edge edge = list.get(i);
                        int destination = edge.dest;
                        //only if edge destination is not present in mst
                        if (inSPT[destination] == false) {
                            ///check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance
                            int newKey =  distance[extractedVertex] + edge.weight ;
                            int currentKey = distance[destination];
                            if(currentKey>newKey){
                                Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                                treeSet.add(p);
                                distance[destination] = newKey;
                            }
                        }
                    }
                }
            }
            //print Shortest Path Tree
            printDijkstra(distance, s);
            return distance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][3];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

  
}
class Edge{
    int src,dest,weight;
    Edge(int src,int des,int weight)
    {
        this.dest=dest;
        this.src=src;
        this.weight=weight;
                
    }
}
class PairComparator implements Comparator<Pair<Integer, Integer>>{

        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            //sort using distance values
            int key1 = o1.getKey();
            int key2 = o2.getKey();
            return key1-key2;
        }
    }