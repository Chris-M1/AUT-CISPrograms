/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

import java.util.Random;

/**
 *
 * @author Chris Miller
 */
public class RandomUtils {

    private static final Random RAND = new Random();

    public static int getRandomInt(int bound) {

        return RAND.nextInt(bound);
    }

    public static int getRandomNumber() {
        return RAND.nextInt(10); // Generates a number between 1 and 9
    }

    public static char getRandomLetter() {
        return (char) (RAND.nextInt(26) + 'A'); // Generates a random letter from A to Z
    }

    public static int getRandomX(int maxWidth) {
        int x = 20 + RAND.nextInt((maxWidth - 40) / 20) * 20; // Ensure alignment with the snake's movement, with a
                                                              // margin of 20 pixels
        return x;
    }

    public static int getRandomY(int maxHeight) {
        int y = 20 + RAND.nextInt((maxHeight - 40) / 20) * 20; // Ensure alignment with the snake's movement, with a
                                                               // margin of 20 pixels
        return y;
    }

}
