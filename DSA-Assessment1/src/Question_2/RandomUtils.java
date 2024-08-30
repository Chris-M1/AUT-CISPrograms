/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author wskin
 */
public class RandomUtils {

    private static final Random RAND = new Random();

    public static int getRandomInt(int bound) {

        return RAND.nextInt(bound);
    }

    public static int getRandomNumber() {
        return RAND.nextInt(11) + 1; // Generates a number between 1 and 9
    }

    public static char getRandomLetter() {
        return (char) (RAND.nextInt(26) + 'A'); // Generates a random letter from A to Z
    }

    public static int getRandomX(int maxWidth) {
        int x = 10 + RAND.nextInt((maxWidth - 20) / 10) * 10; // Ensure at least 10-pixel margin from the left and right
                                                              // edges
        return x;
    }

    public static int getRandomY(int maxHeight) {
        int y = 10 + RAND.nextInt((maxHeight - 20) / 10) * 10; // Ensure at least 10-pixel margin from the top and
                                                               // bottom edges
        return y;
    }

}
