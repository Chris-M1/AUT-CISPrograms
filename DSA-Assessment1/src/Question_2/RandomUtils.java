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
        int x = RAND.nextInt(maxWidth / 10) * 10; // Generate X-axis along the same X-axis as snake
        return x;
    }

    public static int getRandomY(int maxHeight) {
        int y = RAND.nextInt(maxHeight / 10) * 10; // Generate Y-axis along same Y-axis as snake
        return y;
    }

}
