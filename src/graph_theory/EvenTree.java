
package graph_theory;
import static graph_theory.Even.ans;
import static graph_theory.Even.tree;
import java.io.*;
import java.util.*;

public class EvenTree {
    static LinkedList<Integer> adj[];
    static int ans;
    static int[] ar;
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        adj=new LinkedList[t_nodes+2];
        for(int i=0;i<adj.length;i++)
        {
            adj[i]=new LinkedList<>();
        }
        for(int i=0;i<t_from.size();i++)
        {
            adj[t_from.get(i)].add(t_to.get(i));
            adj[t_to.get(i)].add(t_from.get(i));
        }
       return minEdge( t_nodes);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int t_Nodes = Integer.parseInt(tNodesEdges[0]);
        int t_Edges = Integer.parseInt(tNodesEdges[1]);

        List<Integer> t_From = new ArrayList<>();
        List<Integer> t_To = new ArrayList<>();

        for (int i = 0; i < t_Edges; i++) {
            String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            t_From.add(Integer.parseInt(tFromTo[0]));
            t_To.add(Integer.parseInt(tFromTo[1]));
        }

        int res = evenForest(t_Nodes, t_Edges, t_From, t_To);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }    

    private static int minEdge(int n) {
         int visit[] = new int[n+2]; 
        ans = 0; 
      
        dfs( visit, 1); 
      
        return ans; 
    }

    private static int dfs(int[] visit, int node) {
        int num = 0, temp = 0; 
      
        visit[node] = 1; 
      
        for (int i = 0; i < adj[node].size(); i++) 
        { 
            if (visit[adj[node].get(i)] == 0) 
            { 
                
                temp = dfs( visit, adj[node].get(i)); 
                if(temp%2!=0) 
                num += temp; 
                else
                ans++; 
            } 
        } 
      
        return num+1; 
    }
}
