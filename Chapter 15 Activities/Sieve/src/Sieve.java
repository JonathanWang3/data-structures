import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * A program that implements the sieve of Eratosthenes.
*/
public class Sieve
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Compute primes up to which integer?");
        int n = in.nextInt();

        // Your work goes here
        Set<Integer> nums = new HashSet<>();
        boolean prime = false;

        for (int i = 2; i < n; i++){
            nums.add(i);
        }

        for (int i = 2; i < n; i++){
            
            for (int j = 2; j < n; j++) {
                if ((i % j == 0) && (Math.floorDiv(i,j) != 1)) {
                    nums.remove(i);
                }
            }
        }

        System.out.println(nums);







    }
}
