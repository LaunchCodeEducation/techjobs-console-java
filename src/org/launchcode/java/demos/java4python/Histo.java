package org.launchcode.java.demos.java4python;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


/**
 * From "Java for Python Programmers"
 */
public class Histo {

    public static void main(String[] args) {
        Scanner data = null;
        ArrayList<Integer> count;
        Integer idx;

        try {
            data = new Scanner(new File("resources/test.dat"));
        }
        catch ( IOException e) {
            System.out.println("Unable to open your data file");
            e.printStackTrace();
            System.exit(0);
        }

        count = new ArrayList<>();
        for (Integer i =0; i<10; i++) {
            count.add(i,0);
        }

        while(data.hasNextInt()) {
            idx = data.nextInt();
            count.set(idx, count.get(idx)+1);
        }

        idx = 0;
        for(Integer i : count) {
            System.out.println(idx + " occurred " + i + " times");
            idx++;
        }
    }
}
