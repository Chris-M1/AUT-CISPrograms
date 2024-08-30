/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.awt.Point;

/**
 *
 * @author Chris Miller
 */
public class Snake {

    private SnakeBody head;

    public Snake(String initialBody, int startX, int startY) {
        head = new SnakeBody(initialBody.charAt(0), new Point(startX, startY));
        SnakeBody current = head;

        for (int i = 1; i < initialBody.length(); i++) {
            current.setNext(new SnakeBody(initialBody.charAt(i), new Point(startX - i * 10, startY)));
            current.getNext().setPrev(current);
            current = current.getNext();
        }
    }

    public void run(int direction) {
        SnakeBody current = head;
        Point previousLocation = new Point(current.getLocation());

        switch (direction) {
            case 0: // up
                current.setLocation(current.getLocation().x, current.getLocation().y - 20);
                break;
            case 1: // right
                current.setLocation(current.getLocation().x + 20, current.getLocation().y);
                break;
            case 2: // down
                current.setLocation(current.getLocation().x, current.getLocation().y + 20);
                break;
            case 3: // left
                current.setLocation(current.getLocation().x - 20, current.getLocation().y);
                break;
        }

        // Move the rest of the body
        while (current.getNext() != null) {
            current = current.getNext();
            Point newLocation = new Point(current.getLocation());
            current.setLocation(previousLocation.x, previousLocation.y);
            previousLocation = newLocation;
        }
    }

    public void addLetterInOrder(char newLetter) {
        // Create the new letter node with a default location (set it properly later)
        SnakeBody newBody = new SnakeBody(newLetter, new Point(0, 0)); // Initialize with a valid Point

        // Insert the new letter in the correct alphabetical position
        SnakeBody current = head.getNext(); // Start after the head
        SnakeBody previous = head;

        while (current != null && current.getBody() < newLetter) {
            previous = current;
            current = current.getNext();
        }

        // Set the location of the new letter correctly, just behind the head in the
        // direction the snake is moving
        Point previousLocation = previous.getLocation();
        if (previousLocation != null) {
            newBody.setLocation(previousLocation.x, previousLocation.y); // Pass x and y separately
        }

        // Insert newBody between previous and current
        newBody.setNext(current);
        previous.setNext(newBody);
        newBody.setPrev(previous);
        if (current != null) {
            current.setPrev(newBody);
        }
    }

    public void removeLetterAtPosition(int position) {
        if (position < 1) {
            return; // Invalid position, do nothing
        }

        SnakeBody current = head.getNext(); // Skip the head
        int count = 1;

        while (current != null && count < position) {
            current = current.getNext();
            count++;
        }

        if (current != null) {
            // Remove the current node
            SnakeBody previous = current.getPrev();
            SnakeBody next = current.getNext();

            if (previous != null) {
                previous.setNext(next);
            }
            if (next != null) {
                next.setPrev(previous);
            }
        } else {
            // If the position is greater than the list size, remove the last node
            SnakeBody last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            if (last.getPrev() != null) {
                last.getPrev().setNext(null);
            }
        }
    }

    public SnakeBody getHead() {
        return this.head;
    }

    public int getLength() {
        int length = 0;
        SnakeBody current = head;

        while (current != null) {
            length++;
            current = current.getNext();
        }

        return length - 1;
    }

}
