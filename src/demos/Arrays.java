package demos;

/**
 * Created by LaunchCode
 */
public class Arrays {

    public static void main(String[] args) {

        System.out.println("printArrayOfNumbers :: ");
        printArrayOfNumbers();

    }

    public static void printArrayOfNumbers() {

        // Declare and initialize an array of integers
        int[] numbers = {1, 2, 6, 9, 10, 14, 17, 20, 24, 42, 45, 85};

        // Loop over the array and print each number
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
