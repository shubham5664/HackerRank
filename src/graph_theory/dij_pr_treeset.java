/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_theory;


import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import javafx.util.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author Geek
 */
public class dij_pr_treeset {

    private static void printDijkstra(int[] distance, int s) {
     System.out.println("Dijkstra Algorithm: (Adjacency List + TreeSet)");
            for (int i = 0; i <Graph.vertices ; i++) {
                System.out.println("Source Vertex: " + s + " to vertex " +   + i +
                        " distance: " + distance[i]);
            } }

    
     static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class PairComparator implements Comparator<Pair<Integer, Integer>>{

        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            //sort using distance values
            int key1 = o1.getKey();
            int key2 = o2.getKey();
            return key1-key2;
        }
    }

    static class Graph {
        static int vertices;
        public static LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);

//            edge = new Edge(destination, source, weight);
//            adjacencylist[destination].addFirst(edge); //for undirected graph
        }

       
        private static final Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) throws Exception {
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
    static int[] shortestReach(int n, int[][] edges, int s) {
        Graph graph=new Graph(n+1);
            for(int i=0;i<edges.length;i++){
                graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
                 graph.addEdge(edges[i][1], edges[i][0], edges[i][2]);
            }
            boolean[] inSPT = new boolean[n+1];
            //distance used to store the distance of vertex from a source
            int [] distance = new int[n+1];

            //Initialize all the distances to infinity
            for (int i = 0; i <n ; i++) {
                inSPT[i]=false;
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
                    LinkedList<Edge> list = Graph.adjacencylist[extractedVertex];
                    for (int i = 0; i < list.size(); i++) {
                        Edge edge = list.get(i);
                        int destination = edge.destination;
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

        int[] newArray=new int[distance.length-1];
        System.arraycopy(distance, 1, newArray, 0, distance.length-1);
        for(int i=0;i<newArray.length;i++)
        {
            if(newArray[i]==Integer.MAX_VALUE)
            {
                newArray[i]=-1;
            }
        }
        return newArray;
       
      
    }
}

