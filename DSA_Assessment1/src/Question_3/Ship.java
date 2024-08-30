/* 
Which object have you chosen as a monitor object to synchronize your code?
Answer: The Port Object.


Why did you choose that object as a monitor oject to synchronize your code?
Answer: As all ships (threads) require access to the Port object, and require only one access to succeed at a time, synchronising through
the Port object is the most efficient manner.
*/

package Question_3;

/**
 *
 * @author xhu
 */
public class Ship implements Runnable {
    int x;
    int y;
    String name = "Ship";
    Port target;
    Panel panel; // Reference to the Panel
    boolean arrived = false;
    boolean syncMode = false;

    static boolean shipMoving = false;

    public Ship(int x, int y, Port port, Panel panel) { // Add Panel as a parameter
        this.x = x;
        this.y = y;
        this.target = port;
        this.panel = panel; // Assign the panel reference
    }

    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName() + " is running");
        if (syncMode) {
            synchronized (target) { // Synchronize on the Port object
                move();
            }
        } else {
            move(); // Unsynchronized mode allows race conditions
        }
    }

    public void move() {
        while (x != target.x || y != target.y) {
            if (this.x < target.x) {
                this.x++;
            } else if (this.x > target.x) {
                this.x--;
            }
            if (this.y < target.y) {
                this.y++;
            } else if (this.y > target.y) {
                this.y--;
            }
            try {
                Thread.sleep(2); // Adjust this value for smoother/faster animation
                panel.repaint(); // Force the panel to repaint after each move
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }

        if (!syncMode && target.occupied) {
            System.out.println(
                    "Crash! " + Thread.currentThread().getName() + " attempted to dock, but the port was occupied.");
            // panel.displayCrashMessage("Crash! " + Thread.currentThread().getName() + "
            // crashed at the port.");
            return;
        }

        shipMoving = true;
        target.occupied = true;
        panel.triggerBoatImageDisplay();

        System.out.println(Thread.currentThread().getName() + " has arrived at the port.");

        try {
            Thread.sleep(1000); // Stay at the port for 1 second
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted while docked.");
        }

        target.occupied = false;
        shipMoving = false;
        arrived = true;
    }
}
