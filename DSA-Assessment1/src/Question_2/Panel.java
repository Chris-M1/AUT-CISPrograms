/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author xhu
 */
public class Panel extends JPanel implements KeyListener, ActionListener {

    private Snake snake;
    private List<SnakeBody> numbers;
    private SnakeBody letter;
    private int direction; // 0 = up, 1 = right, 2 = down, 3 = left
    private Timer timer;
    private boolean isGameOver = false;

    public Panel() {
        this.snake = new Snake("@", 100, 100); // Initialize the snake
        this.numbers = new ArrayList<>();
        this.direction = 1; // Start moving to the right
        generateNumbersAndLetter();
        setFocusable(true); // Ensure the panel is focused for key events
        addKeyListener(this); // Add key listener to capture arrow key presses

        // Initialize the timer to control the snake's speed
        int initialDelay = 50; // Adjust this value to control the speed (milliseconds)
        timer = new Timer(initialDelay, this);
        timer.start();
    }

    public void start() {
        // start the game
    }

    private void generateNumbersAndLetter() {
        // Ensure the panel has a positive size before generating positions
        int width = getWidth();
        int height = getHeight();

        if (width == 0 || height == 0) {
            width = 800; // Default width
            height = 800; // Default height
        }

        // Generate 10 numbers with random positions
        for (int i = 0; i < 10; i++) {
            int number = RandomUtils.getRandomNumber();
            int x = RandomUtils.getRandomX(width);
            int y = RandomUtils.getRandomY(height);
            numbers.add(new SnakeBody((char) ('0' + number), new Point(x, y)));
        }

        // Generate 1 random letter with a random position
        char randomLetter = RandomUtils.getRandomLetter();
        int x = RandomUtils.getRandomX(width);
        int y = RandomUtils.getRandomY(height);
        letter = new SnakeBody(randomLetter, new Point(x, y));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Set a larger font size for better visibility
        g.setFont(new Font("Ariel", 1, 18)); // Increase font size to 24, can adjust as needed

        if (isGameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over!", getWidth() / 2 - 60, getHeight() / 2); // Adjusted position for larger text
            return;
        }

        // Draw the snake
        drawSnake(g);

        // Draw the letter (food)
        if (letter != null) {
            g.setColor(Color.RED); // Set color for the letter
            g.drawString(String.valueOf(letter.getBody()), letter.getLocation().x, letter.getLocation().y);
        }

        // Draw the numbers
        drawNumbers(g);

        // Display the score
        g.setColor(Color.WHITE); // Set color for the score text
        g.drawString("Score: " + snake.getLength(), 10, 30); // Adjusted position for larger text
    }

    public void drawSnake(Graphics g) {
        SnakeBody body = snake.getHead();
        g.setColor(Color.GREEN); // Set color for the snake
        while (body != null) {
            g.drawString(String.valueOf(body.getBody()), body.getLocation().x, body.getLocation().y);
            body = body.getNext();
        }
    }

    public void drawNumbers(Graphics g) {
        g.setColor(Color.BLUE); // Set color for numbers
        for (SnakeBody number : numbers) {
            g.drawString(String.valueOf(number.getBody()), number.getLocation().x, number.getLocation().y);
        }
    }

    private void checkCollisions() {
        Point headLocation = snake.getHead().getLocation();

        // Debugging: Print snake head position
        System.out.println("Snake Head Position: " + headLocation);

        // Check for collision with the letter
        if (letter != null) {
            System.out.println("Letter Position: " + letter.getLocation()); // Debugging: Print letter position
            if (headLocation.equals(letter.getLocation())) {
                System.out.println("Collision with letter: " + letter.getBody());
                snake.addLetterInOrder(letter.getBody());
                generateNewLetter();
            }
        }

        // Check for collisions with numbers
        for (int i = 0; i < numbers.size(); i++) {
            SnakeBody number = numbers.get(i);
            // System.out.println("Number " + number.getBody() + " Position: " +
            // number.getLocation());

            if (headLocation.equals(number.getLocation()) && snake.getLength() == 0) {
                isGameOver = true;
                displayGameOverMessage();
            }
            if (headLocation.equals(number.getLocation())) {
                System.out.println("Collision with number: " + number.getBody());
                snake.removeLetterAtPosition(number.getBody() - '0');
                numbers.remove(i);
                generateNewNumber();
                break;
            }

        }
    }

    private void checkBorderCollision() {
        Point headLocation = snake.getHead().getLocation();
        int x = headLocation.x;
        int y = headLocation.y;

        // Check if the snake's head is outside the boundaries of the panel
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            isGameOver = true;
            displayGameOverMessage();
        }
    }

    private void displayGameOverMessage() {
        // Stop the timer to stop the game
        timer.stop();

        // Show "Game Over" message
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void generateNewLetter() {
        int width = getWidth();
        int height = getHeight();
        char randomLetter = RandomUtils.getRandomLetter();
        int x = RandomUtils.getRandomX(width);
        int y = RandomUtils.getRandomY(height);
        letter = new SnakeBody(randomLetter, new Point(x, y));
    }

    private void generateNewNumber() {
        int width = getWidth();
        int height = getHeight();
        int number = RandomUtils.getRandomNumber();
        int x = RandomUtils.getRandomX(width);
        int y = RandomUtils.getRandomY(height);
        numbers.add(new SnakeBody((char) ('0' + number), new Point(x, y)));
    }
    /*
     * // public void randomFood() {
     * // // generate a random food
     * // // System.out.println("random food: " + this.food);
     * // char temp = 'a';
     * // if (Math.random() < 0.5) {
     * // temp = (char) (Math.random() * 26 + 'a');
     * // } else {
     * // temp = (char) (Math.random() * 26 + 'A');
     * // }
     * 
     * // Point point = RandomUtils.getRandomPoint();
     * 
     * // SnakeBody body = new SnakeBody(temp, point);
     * // // body.setNext(null);
     * // // body.setPrev(null);
     * // this.food = body;
     * 
     * // // return body;
     * // }
     * 
     * // public void randomNumbers() {
     * // // generate a random number
     * // System.out.println("random number: " + this.numbers);
     * // System.out.print("The numbers are: ");
     * // for (int i = 0; i < 10; i++) {
     * // this.numbers[i].setBody((char) (Math.random() * 10 + '0'));
     * // System.out.print(String.valueOf(numbers[i].getBody()) + " ");
     * // }
     * // }
     * 
     * // public void collisionDectect() {
     * // // detect if the snake hits the food
     * // // if (this.snake.getHead().getX() == this.food.getX() &&
     * // // this.snake.getHead().getY() == this.food.getY()) {
     * // // System.out.println("hit food");
     * // // this.snake.eat(food);
     * // // randomFood();
     * // // }
     * 
     * // if (this.snake.getHead().getLocation().equals(this.food.getLocation())) {
     * // System.out.println("hit food: " + this.food.getBody());
     * // this.snake.eat(food);
     * // randomFood();
     * // }
     * // }
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            snake.run(direction); // Move the snake in the current direction

            checkCollisions(); // Check for collisions with numbers or the letter
            checkBorderCollision(); // Check for collision with the border

            repaint(); // Repaint the panel to show the updated snake position
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but required by KeyListener
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by KeyListener
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                if (direction != 2)
                    direction = 0; // Move up
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 3)
                    direction = 1; // Move right
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 0)
                    direction = 2; // Move down
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 1)
                    direction = 3; // Move left
                break;
        }
    }
}
