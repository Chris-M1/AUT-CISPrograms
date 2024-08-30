package Question_1;

/**
 *
 * @author Chris Miller
 * @param <E>
 */
public class Queue<E extends Comparable<E>> {

    private LinkedList<E> queue = new LinkedList<>();

    public void enqueue(E data) {
        queue.add(data);
    }

    public E dequeue() {
        Node<E> node = queue.removeFromHead();

        return node.data;
    }

    public void printQueue() {
        queue.printLinkedList();
    }

    public int getSize() {
        return queue.size;
    }
}
