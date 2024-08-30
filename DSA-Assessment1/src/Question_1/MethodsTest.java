package Question_1;

/**
 *
 * @author Chris Miller
 */
public class MethodsTest {

    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<Integer>();

        for (int i = 0; i < 10; i++) {
            test.add(i);
        }

        test.addHead(15);

        test.printLinkedList();

        System.out.println("The head of the list is: " + test.getHead());
        System.out.println("The tail of the list is: " + test.getTail());

        System.out.println(test.contains(new Node(9)));
        System.out.println("Size: " + test.size);

        test.remove(10);
        test.printLinkedList();
        System.out.println("Size: " + test.size);
        test.removeFromTail();
        test.printLinkedList();
        System.out.println("Size: " + test.size);
    }
}
