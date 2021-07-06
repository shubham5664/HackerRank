
package graph_theory;

import static graph_theory.Dijkstras_list.adj;
import java.io.*;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class dijk_Hacker {
    private static int dist[];
    private static Set<Integer> settled;
    private static int V;
    private static PriorityQueue<Node1> pq;
    static List<List<Node1>> adj;
    
    private static void dijkstra(List<List<Node1>> adj, int s) {
        dijk_Hacker.adj=adj;
        for(int i=0;i<V;i++)
        {
            dist[i]=Integer.MAX_VALUE;
        }
        pq.add(new Node1(s,0));
        dist[s]=0;
        while(pq.size()!=0)
        {
            int u=pq.remove().node;
            settled.add(u);
            e_Neighbours(u);
        }
    }

    private static void e_Neighbours(int u) {
        int edge_distance=-1;
        int new_distance=-1;
        
        for(int i=0;i<adj.get(u).size();i++)
        {
            Node1 v=adj.get(u).get(i);
            if(!settled.contains(v.node))
            {
                edge_distance=v.cost;
                new_distance=dist[u]+edge_distance;
                if(new_distance<dist[v.node])
                {
                    dist[v.node]=new_distance;
                }
                pq.add(new Node1(v.node, dist[v.node]));
            }
        }
    }
    // Complete the shortestReach function below.
     public dijk_Hacker() {
    }
    
    public dijk_Hacker(int v)
    {
        
        this.V=v;
        dist=new int[V];
        settled=new HashSet<Integer>();
        pq=new PriorityQueue<>(V,new Node1());
        System.out.println("run");
    }
    static int[] shortestReach(int n, int[][] edges, int s) {
        dijk_Hacker dj=new dijk_Hacker(n+1);
         List<List<Node1>> adj1=new ArrayList<>();
        for(int i=0;i<n+1;i++)
        {
            List<Node1> item=new ArrayList<>();
            adj1.add(item);
        }
        for(int i=0;i<n;i++)
        {
           System.out.println(edges[i][0]+"  "+edges[i][1]+"  "+edges[i][2]+"  ");
           adj1.get(edges[i][0]).add(new Node1(edges[i][1], edges[i][2]));
           adj1.get(edges[i][1]).add(new Node1(edges[i][0], edges[i][2]));
        }
        for(int i=0;i<n+1;i++)
        {
            System.out.println(" "+adj1.get(i));
        }
         dijkstra(adj1,s);
//         System.out.println("The shorted path from node :"); 
//        for (int i = 0; i < dj.dist.length; i++) 
//            System.out.println(s + " to " + i + " is "
//                               + dj.dist[i]); 
        return dist;
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
class Node1 implements Comparator<Node1>{
        public int node,cost;

        public Node1(int node, int cost) {
            this.node=node;
            this.cost=cost;
        }

    Node1() {
       }

        @Override
        public int compare(Node1 o1, Node1 o2) {
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