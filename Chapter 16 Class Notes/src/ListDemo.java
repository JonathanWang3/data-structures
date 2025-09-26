/**
 *   A program that demonstrates the LinkedList class
 */
public class ListDemo
{
    public static void main(String[] args)
    {
        LinkedList students = new LinkedList();
        students.addFirst("John");
        students.addFirst("Sarah");
        students.addFirst("Mark");
        students.addFirst("Tanya");

        System.out.println(students);
    }
}
