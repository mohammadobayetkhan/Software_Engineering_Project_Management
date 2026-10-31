### PRIME CHECKER : 
## You are given a class Solution and its main method in the editor. Your task is to create a class Prime. The class Prime should contain a single method checkPrime.

The locked code in the editor will call the checkPrime method with one or more integer arguments. You should write the checkPrime method in such a way that the code prints only the prime numbers.

Please read the code given in the editor carefully. Also please do not use method overloading!

Note: You may get a compile time error in this problem due to the statement below:

  BufferedReader br=new BufferedReader(new InputStreamReader(in));
This was added intentionally, and you have to figure out a way to get rid of the error.

Input Format

There are only five lines of input, each containing one integer.

Output Format

There will be only four lines of output. Each line contains only prime numbers depending upon the parameters passed to checkPrime in the main method of the class Solution. In case there is no prime number, then a blank line should be printed.

Sample Input

2
1
3
4
5
Sample Output

2
2 
2 3 
2 3 5 

## Solution :
class Prime {
    void checkPrime(int... numbers) {
        for (int n : numbers) {
            if (n < 2) continue;

            int count = 0;

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    count++;
                    break;
                }
            }

            if (count == 0) {
                System.out.print(n + " ");
            }
        }

        System.out.println();
    }
}

### Java Primality Test : 
## A prime number is a natural number greater than  whose only positive divisors are  and itself. For example, the first six prime numbers are , , , , , and .

Given a large integer, , use the Java BigInteger class' isProbablePrime method to determine and print whether it's prime or not prime.

Input Format

A single line containing an integer,  (the number to be checked).

Constraints

 contains at most  digits.
Output Format

If  is a prime number, print prime; otherwise, print not prime.

Sample Input

13
Sample Output

prime
Explanation

The only positive divisors of  are  and , so we print prime.

# Solution : 
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BigInteger n = sc.nextBigInteger();

        if (n.isProbablePrime(10)) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }

        sc.close();
    }
}

## LeaderBoard : <img width="1685" height="782" alt="image" src="https://github.com/user-attachments/assets/b3a80c4a-97ce-43de-806d-761df26ba1e5" />


