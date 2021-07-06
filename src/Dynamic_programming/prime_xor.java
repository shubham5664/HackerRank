
package Dynamic_programming;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import javax.swing.text.html.HTMLDocument;

public class prime_xor {
    static int count=0;
     static int primeXor(int[] a) {
         int flag = 0,result=0,sum=0;
         Set<Integer> s=new HashSet<>();
         for(int i=0;i<a.length;i++)
         {
             s.add(a[i]);
//             checkPrime(a[i]);
         }
     
             Iterator it=s.iterator();
             while(it.hasNext())
             {
                 int p=(int) it.next();
                 checkPrime(p);
             }
      
         System.out.println("set:"+s);
         BigInteger x=BigInteger.valueOf(a[0]);
         BigInteger y=BigInteger.valueOf(a[1]);
         BigInteger xorANS=x.xor(y);
         for(int i=2;i<a.length;i++)
         {
             BigInteger z=BigInteger.valueOf(a[i]);
             xorANS=xorANS.xor(z);
             
         }
         checkPrime(xorANS.intValue());
         System.out.println("xor value:"+xorANS.intValue());
         
         return (int) (count%(Math.pow(10, 9)+7));


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int aItem = Integer.parseInt(aItems[i]);
                a[i] = aItem;
            }

            int result = primeXor(a);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    private static void checkPrime(int a) {
       
        int m=a/2,flag=0;
            for(int j=2;j<=m;j++)
            {
                if(a%j==0)
                {
                    flag=1;
                }
            }
            if(flag==0){
                count++;
            }
    }
}
