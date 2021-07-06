
package graph_theory;

import java.util.*;

public class Dijkstras_list {
    private static int dist[];
    private static Set<Integer> settled;
    private static int V;
    private static PriorityQueue<Node> pq;
    static List<List<Node>> adj;

   

    public Dijkstras_list() {
    }
    
    public Dijkstras_list(int v)
    {
        this.V=v;
        dist=new int[V];
        settled=new HashSet<Integer>();
        pq=new PriorityQueue<>(V,new Node());
    }
    public static void main(String args[])
    {
        int V=5;
        int source=1;
        List<List<Node>> adj=new ArrayList<>();
        for(int i=0;i<V;i++)
        {
            List<Node> item=new ArrayList<>();
            adj.add(item);
        }
        adj.get(1).add(new Node(2,24));
         adj.get(1).add(new Node(4, 20)); 
        adj.get(3).add(new Node(1, 3)); 
        adj.get(4).add(new Node(3, 12)); 
   
         for(int i=0;i<V;i++)
        {
            for(int j=0;j<adj.get(i).size();j++)
            {
                System.out.print(adj.get(i).get(j).node+" ");
            }
            System.out.println("");
            
        }
        Dijkstras_list dg=new Dijkstras_list();
        Dijkstras_list dg1=new Dijkstras_list(V);
        dijkstra(adj,source);
        System.out.println("The shorted path from node :"); 
        for (int i = 0; i < dg.dist.length; i++) 
            System.out.println(source + " to " + i + " is "
                               + dg.dist[i]); 
        
        
    }
    private static void dijkstra(List<List<Node>> adj, int source)
    {
        Dijkstras_list.adj=adj;
        for(int i=0;i<V;i++)
        {
            dist[i]=Integer.MAX_VALUE;
        }
        pq.add(new Node(source,0));
        dist[source]=0;
        while(settled.size()!=V)
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
            Node v=adj.get(u).get(i);
            if(!settled.contains(v.node))
            {
                edge_distance=v.cost;
                new_distance=dist[u]+edge_distance;
                if(new_distance<dist[v.node])
                {
                    dist[v.node]=new_distance;
                }
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
     }
}
class Node implements Comparator<Node>{
    public int node,cost;
    public Node()
    {
        
    }
    public Node(int node,int cost)
    {
        this.cost=cost;
        this.node=node;
    }
    @Override
    public int compare(Node o1, Node o2) {
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
