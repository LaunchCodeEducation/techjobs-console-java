package org.launchcode.java.demos.java4python;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * From "Java for Python Programmers"
 */
public class HistoMap {

    public static void main(String[] args) {
        Scanner data = null;
        HashMap<String,Integer> count;
        Integer idx;
        String word;
        Integer wordCount;

        try {
            data = new Scanner(new File("resources/alice.txt"));
        } catch ( IOException e) {
            System.out.println("Unable to open your data file");
            e.printStackTrace();
            System.exit(0);
        }

        count = new HashMap<String,Integer>();

        while(data.hasNext()) {
            word = data.next().toLowerCase();
            wordCount = count.get(word);
            if (wordCount == null) {
                wordCount = 0;
            }
            count.put(word, ++wordCount);
        }

        for(String i : count.keySet()) {
            System.out.printf("%-20s occured %5d times\n", i, count.get(i) );
        }
    }
}

