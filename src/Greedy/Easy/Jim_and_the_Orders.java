

package Greedy.Easy;

import java.io.*;
import java.text.Collator;

import java.util.*;
class Node{
    int key,value;
    Node(int key,int value)
    {
        this.key=key;
        this.value=value;
    }
}
public class Jim_and_the_Orders {
     static int[] jimOrders(int[][] orders) {
         int[] result=new int[orders.length];
         List<Node> l=new ArrayList<>();
         Node n;
        for(int i=0;i<orders.length;i++)
        {
            n=new Node(i, orders[i][0]+orders[i][1]);
            result[i]=orders[i][0]+orders[i][1];
            l.add(n);    
        }
         
     int[] a=new int[result.length];
        Arrays.sort(result);
       
        for(int i=0;i<result.length;i++)
        {
            for(int j=0;j<l.size();j++)
            {
                if(result[i]==l.get(j).value)
                {
                    System.out.println("list:"+l.get(j).key);
                    a[i]=l.get(j).key;
                    System.out.println("a[i]:"+a[i]);
                }
            }
        }
        return a; 
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] orders = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] ordersRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int ordersItem = Integer.parseInt(ordersRowItems[j]);
                orders[i][j] = ordersItem;
            }
        }

        int[] result = jimOrders(orders);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

