
package graph_theory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


public class Even {
    static int ans;
     static Vector<Vector<Integer>> tree=new Vector<Vector<Integer>>(); 
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        for(int i=0;i<t_nodes+2;i++)
        {
            Even.tree.add(new Vector<>());
        }
        for(int i=0;i<t_from.size();i++)
        {
            Even.tree.get(t_from.get(i)).add(t_to.get(i));
            Even.tree.get(t_to.get(i)).add(t_from.get(i));
        }
        for(int i=0;i<tree.size();i++)
        {
            System.out.println(""+tree.get(i));
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
      
        for (int i = 0; i < tree.get(node).size(); i++) 
        { 
            if (visit[tree.get(node).get(i)] == 0) 
            { 
                
                temp = dfs( visit, tree.get(node).get(i)); 
                if(temp%2!=0) 
                num += temp; 
                else
                ans++; 
            } 
        } 
      
        return num+1; 
    }

    
   
}
