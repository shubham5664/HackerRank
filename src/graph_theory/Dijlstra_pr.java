/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_theory;

import java.io.*;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 *
 * @author Geek
 */
public class Dijlstra_pr {
     private static int[] dist;
    private static Set<Integer> settled;
    private static int V;
    private static PriorityQueue<Node2> pq;
    static List<List<Node2>> adj;

    private static void dijkstra(List<List<Node2>> adj, int src)throws Exception{
          Dijlstra_pr.adj = adj; 
  
        for (int i = 0; i <V; i++) {
            dist[i] = Integer.MAX_VALUE;
        } 
        pq.add(new Node2(src, 0)); 
        dist[src] = 0; 
        while (pq.size()!=0) { 
  
            
            int u = pq.poll().node;
   

            settled.add(u); 
  
            e_Neighbours(u); 
        } 
    }

    private static void e_Neighbours(int u) {
        int edgeDistance = -1; 
        int newDistance = -1; 
  
       
        for (int i = 0; i < adj.get(u).size(); i++) { 
            Node2 v = adj.get(u).get(i); 
  
           
            if (!settled.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
  
              
                if (newDistance < dist[v.node]) 
                    dist[v.node] = newDistance; 
  
                
                pq.add(new Node2(v.node, dist[v.node])); 
            } 
        } 
    }

    

    public Dijlstra_pr() {
    }
    public Dijlstra_pr(int V)
    {
         Dijlstra_pr.V=V;
         settled=new HashSet<Integer>();
         pq=new PriorityQueue<>(V,new Node2());
         dist=new int[V];
         
    }
    static int[] shortestReach(int n, int[][] edges, int s) throws Exception {
        Dijlstra_pr dj=new Dijlstra_pr(n+1);
         List<List<Node2>> adj1=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            List<Node2> item=new ArrayList<>();
            adj1.add(item);
        }
        for(int i=0;i<edges.length;i++)
        {
           System.out.println(edges[i][0]+"  "+edges[i][1]+"  "+edges[i][2]+"  ");
           adj1.get(edges[i][0]).add(new Node2(edges[i][1], edges[i][2]));
           adj1.get(edges[i][1]).add(new Node2(edges[i][0], edges[i][2]));
        }
         dijkstra(adj1,s);
         int targetIndex = 0;
        for( int sourceIndex = 0;  sourceIndex < dist.length;  sourceIndex++ )
        {
            
            if( dist[sourceIndex] != 0 && dist[sourceIndex]!=Integer.MAX_VALUE)
            {
                dist[targetIndex++] = dist[sourceIndex];
            }
            else if(dist[sourceIndex]==Integer.MAX_VALUE)
            {
                dist[targetIndex++]=-1;
            }
            
            
        }
        int[] newArray = new int[targetIndex-1];
        System.arraycopy( dist, 1, newArray, 0, targetIndex -1);
        
        return newArray;
       
      
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, Exception {
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
class Node2 implements Comparator<Node2>
{
    int node,cost;
        public Node2() {
        }
    public Node2(int node,int cost)
    {
        this.node=node;
        this.cost=cost;
    }
    @Override
    public int compare(Node2 o1, Node2 o2) {
        if(o1.cost<o2.cost)
        {
            return -1;
        }
        if(o1.cost>o2.cost)
        {
            return 1;
        }
        return 0;
    }
}
