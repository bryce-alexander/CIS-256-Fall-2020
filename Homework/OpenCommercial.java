/* OpenCommercial.java */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    // Inserts input into URL format as string
    String urlString = "https://www." + inputLine + ".com/";

    // Converts string to a URL
    URL urltest = new URL(urlString);

    // Creates scanner with a source of the URL created
    Scanner URLscanner = new Scanner(urltest.openStream());

    // Initialize an ArrayList, iterates and stores first five lines
    ArrayList<String> lineList = new ArrayList(5);
    for (int i = 0; i < 5; i++) {
      lineList.add(URLscanner.nextLine());
    }

    // Prints the five lines which were stored in reverse order
    for (int i = 4; i>=0; i--) {
      System.out.println(lineList.get(i));
    }


  }
}
