/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy.Easy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Geek
 */
public class Grid_Challenge {
    static String gridChallenge(String[] grid) {
        
        
            char[] charArray;
            System.out.println("row:"+grid.length);
            System.out.println("col:"+grid[0].length());
            char[][] a=new char[grid.length][grid[0].length()];
        for(int i=0;i<grid.length;i++)
        {
            charArray=grid[i].toCharArray();
            Arrays.sort(charArray);
            grid[i]=new String(charArray);
        }
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length();j++)
            {
                a[i][j]=grid[i].charAt(j);
            }
        }
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<grid[0].length();j++)
            {
                System.out.print(a[i][j]+" ");
            }
            System.out.println("");
        }
        boolean x = false;
        char[] c=new char[grid.length];
        if(checkColumn(a))
        {
            return "YES";
        }
        else
        {
            return "NO";
        }
        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String[] grid = new String[n];

            for (int i = 0; i < n; i++) {
                String gridItem = scanner.nextLine();
                grid[i] = gridItem;
            }

            String result = gridChallenge(grid);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    private static boolean checkColumn(char[][] a) {
        for(int i=0;i<a[0].length;i++)//column
        {
            for(int j=1;j<a.length;j++)//row
            {
                if(a[j][i]<a[j-1][i])
                {
                    return false;
                }
            }
        }
        return true;
    }

   

   
   
}
