import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
/**
 * Write a program that checks whether a sequence of HTML tags
 * is properly nested. For each opening tag, such as <p>, there
 * must be a closing tag </p>. A tag such as <p> may have other
 * tags inside, for example <p> <ul> <li> </li> </ul> <a> </a> </p>
 * <p>
 * The inner tags must be closed before the outer ones.
 * Your program should process a file containing tags.
 * For simplicity, assume that the tags are separated by
 * spaces, and that there is no text inside the tags.
*/
public class HTMLChecker
{
    public static void main(String[] args)
    {
        String filename = "TagSample1.html";

        try (Scanner in = new Scanner(new File(filename)))
        {
            // Your code goes here
            Stack<String> stack = new Stack<>();
            boolean isBalanced = true;
            while (in.hasNext() && isBalanced) {
                String tag = in.next();
                if (tag.contains("<") && !tag.contains("</")) {
                    stack.push(tag);
                } else if (tag.contains("</")) {
                    if (stack.isEmpty()) {
                        isBalanced = false;
                    } else {
                        String openTag = stack.pop();
                        String expectedCloseTag = "</" + openTag.substring(1);
                        if (!tag.equals(expectedCloseTag)) {
                            isBalanced = false; 
                        }
                    }
                }
                
            }
            System.out.println("The tags are balanced: " + isBalanced);


        } catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }

    }

}
