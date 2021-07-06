
package graph_theory;


public class Dijkstra {
    static final int v=9;
    public static void main(String args[])
    {
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
        dijkstra(graph,0);
    }

    private static void dijkstra(int[][] graph, int source) {
        
        int[] dist=new int[graph.length];//store distances from source node
        dist[source]=0;
        Boolean[] sptset=new Boolean[graph.length];
        
        for(int i=0;i<v;i++)
        {
            dist[i]=Integer.MAX_VALUE;
            sptset[i]=false;
        }
        
        dist[source]=0;
        for(int i=0;i<v-1;i++)
        {
            int u=minDistance(sptset,dist);
            sptset[u]=true;
            
            for(int j=0;j<v;j++)
            {
               if (!sptset[j] && graph[u][j] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][j] < dist[j]) 
                    dist[j] = dist[u] + graph[u][j]; 
            }
                
        }
        print(dist);
        
    }

    private static int minDistance(Boolean[] sptset, int[] dist) {
        int min=Integer.MAX_VALUE,min_index=-1;
        for(int i=0;i<v;i++)
        {
            if(sptset[i]==false && dist[i]<=min)
            {
                min=dist[i];
                min_index=i;
            }
        }
        return min_index;
    }

    private static void print(int[] dist) {
        for (int i = 0; i < v; i++) 
            System.out.println(i + " \t\t " + dist[i]); 
    }
}
