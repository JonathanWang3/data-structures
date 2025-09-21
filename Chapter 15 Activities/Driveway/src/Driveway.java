import java.util.Scanner;
import java.util.Stack;

/**
 * Class for simulating a driveway and a street, using stacks
 * of cars with license plate numbers as representation.
*/
public class Driveway
{
    /**
      * Stack representing the cars in the driveway.
    */
    private Stack<Integer> driveway;
    /**
      * Stack representing the cars in the street.
    */
    private Stack<Integer> street;
    private Scanner scanner = new Scanner(System.in);
    private int licensePlate;

    /**
      * Constructor.
    */
    public Driveway()
    {
        // Complete the constructor
        driveway = new Stack<>();
        street = new Stack<>();
        


    }

    /**
      * Add the given license plate to the driveway.
      *
      * @param licensePlate number of license plate.
    */
    public void add(int licensePlate)
    {
        // Complete this method
        driveway.push(licensePlate);
        print();

    }

    /**
      * Remove the given license plate from the driveway.
      *
      * @param licensePlate number of license plate.
    */
    public void remove(int licensePlate)
    {
        // Complete this method
        while (!driveway.isEmpty()) {
            int topPlate = driveway.pop();
            if (topPlate == licensePlate) {
                break; // Found and removed the car
            } else {
                street.push(topPlate); // Move to street temporarily
            }
        }
        print();

        while (!street.isEmpty()) {
            driveway.push(street.pop()); // Move cars back to driveway
        }
        print();


    }

    /**
      * Prints the driveway and street details to the screen.
    */
    public void print()
    {
        System.out.println("In Driveway, starting at first in (one license plate per line):");
        // Print the cars in the driveway here
        for (Integer plate : driveway) {
            System.out.println(plate);
        }

        System.out.println("In Street, starting at first in (one license plate per line):");
        // Print the cars in the street here
        for (Integer plate : street) {
            System.out.println(plate);
        }
    }
}
