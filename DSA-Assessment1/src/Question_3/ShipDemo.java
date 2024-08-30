package Question_3;

import javax.swing.JFrame;

/**
 *
 * @author xhu
 */
public class ShipDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();

        frame.getContentPane().add(panel);
        frame.setSize(1000, 1050);
        frame.setVisible(true);
    }

}
