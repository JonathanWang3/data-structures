import java.util.LinkedList;

/**
 * This class supplies a utility method to reverse the entries in a linked list.
*/
public class ListUtil
{
    /**
     * Reverses the elements in a linked list
     *
     * @param strings the linked list to reverse
    */
    public static void reverse(LinkedList<String> strings)
    {
        // copy strings into new list loop
        LinkedList<String> og = strings;
        for (String s : strings)
        {
            strings.remove(s);
        }
        LinkedList<String> fill = new LinkedList<>();
        LinkedList<String> newL = remover(fill,og);
        //System.out.println(newL);
        strings.clear();
        

        for (String s : newL)
        {
            strings.add(s);
        }

        System.out.println(strings);
        //return newL;

    }

    public static LinkedList remover(LinkedList<String> strings, LinkedList<String> oG)
    {
        LinkedList<String> newList = strings;
        if (!oG.isEmpty())
        {
            
            newList.add(oG.removeLast());
            return remover(newList, oG);
        }
        else
        {
            return newList;
        }
    }
}