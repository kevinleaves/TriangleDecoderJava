import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
      boolean flag = true;
      File inputFile;
      ArrayList<Pair> rows;

      while (flag) {
        System.out.println("Provide a file name to read from: , or type 'quit' to exit");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        inputFile = new File(userInput);
        // readInput can throw a NumberFormatException, so we wrap it in a try/catch.
        /*
         * Caught Error Prevents the program from crashing when provided an invalid
         * input.
         */
        try {
          if (inputFile.exists()) {
            rows = readInput(userInput);

            if (rows.size() == 0) {
              System.out.println("File is empty. Please try again.");
              continue;
            }

            sortInput(rows, new PairComparator());
            ArrayList<ArrayList<Pair>> triangle = generateTriangle(rows);
            String result = decode(triangle);
            System.out.println("Decoded message: " + result);
          } else if (userInput.equalsIgnoreCase("quit")) {
            System.out.println("Program Exited");
            sc.close();
            flag = false;
          } else {
            System.out.println("File not found. Please try again.");
          }
        } catch (NumberFormatException e) {
          System.out.println(
              "There was an error with the input file. Please fix the file or try again with another file.");
          System.out.println(e);
        }
      }
    }

    public static ArrayList<Pair> readInput(String inputFile) {
      ArrayList<Pair> rows = new ArrayList<>();
      File textFile = new File(inputFile);

      try {
        Scanner fileScanner = new Scanner(textFile);

        while (fileScanner.hasNextLine()) {
          String line = fileScanner.nextLine();
          rows.add(new Pair(line));
        }

        fileScanner.close();
        return rows;
      } catch (FileNotFoundException e) {
        System.out.println(e);
      } catch (NumberFormatException e) {
        throw e;
      }
      return rows;
    }

    // sort array in place
    public static void sortInput(ArrayList<Pair> rows, Comparator<Pair> c) {
      Collections.sort(rows, c);
    }

    public static ArrayList<ArrayList<Pair>> generateTriangle(ArrayList<Pair> pairs) {
      ArrayList<ArrayList<Pair>> triangle = new ArrayList<ArrayList<Pair>>();

      ArrayList<Pair> layer = new ArrayList<Pair>();
      int rowLength = 1;

      for (Pair pair : pairs) {
        layer.add(pair);
        if (layer.size() == rowLength) {
          rowLength += 1;
          triangle.add(layer);
          layer = new ArrayList<Pair>();
        }
      }

      return triangle;
    }

    public static String decode(ArrayList<ArrayList<Pair>> triangle) {
      // decode pyramid
      String decoded = "";
      for (int i = 0; triangle.size() > i; i++) {
        ArrayList<Pair> row = triangle.get(i);
        Pair firstPair = row.get(0);
        decoded += firstPair.getWord();
        decoded += " ";
      }
      return decoded;
    }
    // @Test
    // void addition() {
    // assertEquals(2, 1 + 1);
    // }
  }
