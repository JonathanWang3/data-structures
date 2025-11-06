/**
    This class implements a binary search tree whose
    nodes hold objects that implement the Comparable
    interface.
*/
public class BinarySearchTree
{   
    private Node root;

    /**
        Constructs an empty tree.
    */
    public BinarySearchTree()
    {   
        
    }
    
    /**
        Inserts a new node into the tree.
        @param obj the object to insert
    */
    public void add(Comparable obj) 
    {   
        Node newNode = new Node();
        newNode.data = obj;
        newNode.left = null;
        newNode.right = null;

        if (this.root == null) {
            this.root = newNode;
            return;
        }
        this.root.addNode(newNode);
    }

    /**
        Tries to find an object in the tree.
        @param obj the object to find
        @return true if the object is contained in the tree
    */
    public boolean find(Comparable obj)
    {
        Node current = this.root;
        while (current != null) {
            int diff = obj.compareTo(current.data);
            if (diff == 0) {
                return true;
            } else if (diff < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }
    
    /**
        Tries to remove an object from the tree. Does nothing
        if the object is not contained in the tree.
        @param obj the object to remove
    */
    public void remove(Comparable obj)
    {
        Node toBeRemove = this.root;
        boolean found = false;
        Node parent = null;

        while (!found && toBeRemove != null) {
            int diff = obj.compareTo(toBeRemove.data);
            if (diff == 0) {
                found = true;
            } else if (diff < 0) {
                toBeRemove = toBeRemove.left;
            } else {
                toBeRemove = toBeRemove.right;
            }
        }

        if (!found) {
            return;
        }

        // case 1 and case 2 (at least one child is null)
        if (toBeRemove.left == null || toBeRemove.right == null) {
            Node newChild;

            if (toBeRemove.left == null) {
                newChild = toBeRemove.right;
            } else {
                newChild = toBeRemove.left;
            }

            // Remove the root if the parent is null
            if (parent == null) {
                this.root = newChild;
            }
            else {
                parent.right = newChild;
            }

            return;
        }

        // case 3: remove a node with two children

        // Find the least element of the right subtree
        // The least element will replace the removed node
        Node leastParent = toBeRemove;
        Node least = toBeRemove.right;
        while (least.left != null) {
            leastParent = least;
            least = least.left;
        }

        // move the data to the node being removed
        toBeRemove.data = least.data;

        // unlink the last child
        if (leastParent == toBeRemove) {
            leastParent.right = least.right;
        } else {
            leastParent.left = least.right;
        }
    }
    
    /**
        Prints the contents of the tree in sorted order.
    */
    public void print()
    {   
        // Print the tree using inorder traversal
        System.out.println();
    }   

    /**
        Prints a node and all of its descendants in sorted order.
        @param parent the root of the subtree to print
    */
    private static void print(Node parent)
    {   
        if (parent == null) {
            return;
        }

        print(parent.left);
        System.out.print(parent.data + " ");
        print(parent.right);
    }

    /**
        A node of a tree stores a data item and references
        to the left and right child nodes.
    */
    static class Node
    {   
        // A BST MUST BE made of Comparable Objects
        public Comparable data;
        public Node left;
        public Node right;

        /**
            Inserts a new node as a descendant of this node.
            @param newNode the node to insert
        */
        public void addNode(Node newNode)
        {   
            // If diff < 0, newNode is to the left of this node
            // If diff > 0, newNode is to the fight of this node
            int diff = newNode.data.compareTo(this.data);

            if (diff < 0) {
                if (this.left == null) {
                    this.left = newNode;
                } else {
                    this.left.addNode(newNode);
                }
            } else if (diff > 0){
                if (this.right == null) {
                    this.right = newNode;
                } else {
                    this.right.addNode(newNode);
                }
            }
        }
    }
}



