package Question_1;

/**
 *
 * @author Chris Miller
 * @param <E>
 */
public class DataAnalysis<E extends Comparable<E>> {
    private E[] data;

    public DataAnalysis(E[] data) {
        this.data = data;
    }

    public boolean isPalindrome() {
        Queue<E> rData = reverse(data);

        for (E data1 : data) {
            if (!data1.equals(rData.dequeue())) {
                return false;
            }
        }
        return true;
    }

    public Queue<E> reverse(E[] data) {

        Stack<E> inOrder = new Stack();
        for (E e : data) {
            inOrder.push(e);
        }
        System.out.println("In order list: ");
        inOrder.printStack();

        Queue<E> outOrder = new Queue();
        while (inOrder.getSize() > 0) {
            E tempdata = inOrder.pop();
            outOrder.enqueue(tempdata);
        }
        System.out.println("Out order list: ");
        outOrder.printQueue();

        return outOrder;
    }
}
