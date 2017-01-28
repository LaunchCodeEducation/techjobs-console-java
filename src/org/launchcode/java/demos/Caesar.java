package org.launchcode.java.demos;

import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class Caesar {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Get key and phrase from user
        System.out.println("Rotation key: ");
        int key = in.nextInt();

        System.out.println("Phrase: ");
        String phrase = in.nextLine();

        // Encrypt and print results
        String encrypted = rot(key, phrase);
        System.out.println(encrypted);
    }

    public static String rot(Integer key, String phrase) {

        String encrypted = "";
        for (char c : phrase.toCharArray()) {

            if (c == ' ') {
                encrypted += ' ';
            } else {
                Integer new_idx = (Character.getNumericValue(c) + 13) % 26;
            }
        }



        return encrypted;
    }
}
