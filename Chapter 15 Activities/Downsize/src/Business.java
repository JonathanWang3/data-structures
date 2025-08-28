import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Business utility methods.
*/
public class Business
{
    /**
      * Removes every nth element from the linked list
      *
      * @param employeeNames the linked list to remove from
      * @param n                 the parameter to determine "nth"
     */

    public static void downsize(LinkedList<String> employeeNames, int n)
    {
        ListIterator<String> iterator = employeeNames.listIterator();
        int count = 1;
        while (iterator.hasNext()) {
            iterator.next();
            if (((iterator.nextIndex() + count ) % n) == 1) {
                iterator.remove();
                count += 1;
            }
            
        }
        /* for (String guy : employeeNames) {
            System.out.println(employeeNames.indexOf(guy) % 2 == 1);
            if (employeeNames.indexOf(guy) % 2 == 1) {
                System.out.println("hi");
            }
        } */
    }
}
