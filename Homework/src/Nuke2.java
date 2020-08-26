import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nuke2 {

    public static void main(String[] args) throws Exception {

        // Creates string object to store keyboard input, listens for input from user
        String inputString;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        inputString = keyboard.readLine();

        // Concatenates first character with all characters after second character
        // This excludes the second character in the string
        String test1 = inputString.substring(0,1) + inputString.substring(2);

        // Prints string with second character removed
        System.out.println(test1);


    }
}
