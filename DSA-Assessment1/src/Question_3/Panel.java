package Question_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 *
 * @author xhu
 */
public class Panel extends JPanel implements KeyListener, ActionListener {

    int number_ship = 20;
    boolean program_starts = false;
    boolean modeSelected = false;
    boolean syncMode = false;
    Ship[] ships = new Ship[number_ship];
    Port port;

    Image ship_image;
    Image island_image;
    Image boat_island_image;
    boolean showBoatIslandImage = false; // Track when to show boat_island_image

    int currentShipIndex = 0; // Tracks next ship thread

    Thread[] threads = new Thread[number_ship];
    JButton syncButton = new JButton("Sync Mode");
    JButton unyncButton = new JButton("Unsync Mode");

    public Panel() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        port = new Port(900, 500);

        for (int i = 0; i < number_ship; i++) {
            ships[i] = new Ship(20, i * 50, port, this); // Pass the Panel instance to Ship
            ships[i].syncMode = syncMode; // Set syncMode for each ship
            threads[i] = new Thread(ships[i]);
        }

        ship_image = new ImageIcon("boat.png").getImage();
        island_image = new ImageIcon("land.png").getImage();
        boat_island_image = new ImageIcon("boat_land.png").getImage();

        syncButton.addActionListener(this);
        unyncButton.addActionListener(this);
        this.add(syncButton, BorderLayout.CENTER);
        this.add(unyncButton, BorderLayout.CENTER);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the ships
        for (Ship ship : ships) {
            g.drawImage(ship_image, ship.x, ship.y, this);
        }

        // draw island
        if (showBoatIslandImage) {
            g.drawImage(boat_island_image, port.x, port.y, this);
        } else {
            g.drawImage(island_image, port.x, port.y, this);
        }

        drawNotification(g);

        // set button invisible after the game starts
        hidButton();
    }

    public void triggerBoatImageDisplay() {
        showBoatIslandImage = true;
        repaint();
        // Timer to show docked ship
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBoatIslandImage = false;
                repaint();
            }
        }).start();
    }

    private void hidButton() {
        if (program_starts) {
            syncButton.setVisible(false);
            unyncButton.setVisible(false);
        } else {
            syncButton.setVisible(true);
            unyncButton.setVisible(true);
        }
    }

    public void drawNotification(Graphics g) {
        // check if ships are crashed
        int arrived_ship = 0;
        for (int i = 0; i < number_ship; i++) { // first check if the ship is moving
            for (int j = i + 1; j < number_ship; j++) {
                if (i != j && !ships[i].arrived && !ships[j].arrived) { // avoid checking the same ship, and the ships
                                                                        // already arrived
                    if (ships[i].x == ships[j].x && ships[i].y == ships[j].y) {
                        // draw notification when the ship is crashed
                        g.setFont(new Font("Monospaced", Font.BOLD, 30));
                        g.drawString("Crashed!", ships[i].x, ships[i].y - 50);
                    }
                }
            }
            if (ships[i].arrived) {
                arrived_ship++;
            }
        }

        // draw notification when all ships arrived
        if (arrived_ship == number_ship) {
            g.setFont(new Font("Monospaced", Font.BOLD, 30));
            g.drawString("Game Over", 400, 500);
        }
    }

    private void setSyncModeForShips(boolean syncMode) {
        for (Ship ship : ships) {
            ship.syncMode = syncMode;
        }
    }

    @Override // Check if synchronized or unsychronized is selected.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == syncButton) {
            modeSelected = true;
            syncMode = true;
            setSyncModeForShips(true);
            program_starts = true;
            hidButton();
        } else if (e.getSource() == unyncButton) {
            modeSelected = true;
            syncMode = false;
            setSyncModeForShips(false);
            program_starts = true;
            hidButton();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) { // switch the program_starts flag
        System.out.println("\"" + ke.getKeyChar() + "\" is typed.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { // Check if the space key is pressed
            if (!modeSelected) {
                return; // Do nothing if no mode has been selected
            }

            if (currentShipIndex < number_ship) {
                threads[currentShipIndex].start(); // Start the current ship's thread
                currentShipIndex++; // Move to the next ship for the next space key press
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // placeholder
    }

}
