package Question_3;

/**
 *
 * @author xhu
 */
public class Port {

    int x;
    int y;
    String name = "Port";
    boolean occupied = false; // if a ship already arrived, occupied

    public Port(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;

    }

}
