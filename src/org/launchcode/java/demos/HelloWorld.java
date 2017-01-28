package org.launchcode.java.demos;

import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class HelloWorld {

    public static void main(String[] args) {

        // Prompt user for input
        System.out.println("What's your name? ");

        // Get input
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        // Print a friendly message
        System.out.println("Hello, " + name + "!");

        String string1 = new String("LaunchCode");
        String string2 = new String("LaunchCode");

        System.out.println(string1 == string2);
        System.out.println(string1.equals(string2));
    }
}
